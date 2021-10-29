package fr.crew.garage.domain.search;

import fr.crew.garage.domain.search.entity.CrewSearchEntity;
import fr.crew.garage.domain.skill.entity.SkillEntity;
import fr.crew.garage.domain.skill.repository.SkillRepository;
import fr.crew.garage.domain.team.entity.TeamEntity;
import fr.crew.garage.domain.team.entity.TeammateEntity;
import fr.crew.garage.domain.team.repository.TeamRepository;
import fr.crew.garage.domain.team.repository.TeammateRepository;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.constraints.extension.Tuples;
import org.chocosolver.solver.variables.IntVar;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Service
public class SearchDomainService {

    Logger logger = LoggerFactory.getLogger(SearchDomainService.class);

    final
    TeammateRepository teammateRepository;

    final
    TeamRepository teamRepository;

    final
    SkillRepository skillRepository;

    final
    ModelMapper modelMapper;


    private final int nobody = 0;

    public SearchDomainService(TeammateRepository teammateRepository, TeamRepository teamRepository, SkillRepository skillRepository, ModelMapper modelMapper) {
        this.teammateRepository = teammateRepository;
        this.teamRepository = teamRepository;
        this.skillRepository = skillRepository;
        this.modelMapper = modelMapper;
    }

    public Collection<Crew> search(List<CrewSearchEntity> crewSearches) {

        Collection<Crew> result = new ArrayList<>();

        Model model = new Model("team search problem");
        List<IntVar> vars = new ArrayList<>();
        HashMap<IntVar, CrewMember> mapping = new HashMap<>();

        for (CrewSearchEntity crew : crewSearches) {
            List<CrewMember> members = new ArrayList<>();
            List<IntVar> varsByCrew = new ArrayList<>();
            logger.info("The crew " + crew.getName() + " needs " + crew.getSkills().size() + " members");
            for (SkillEntity skill : crew.getSkills()) {
                // On crée une variable pour trouver chaque membre de l equipage
                IntVar var = model.intVar("Q_" + crew.getName() + "_" + skill.getName(), possibleTeammateIdForSkill(skillRepository.getById(skill.getId())));
                vars.add(var);
                varsByCrew.add(var);

                CrewMember member = new CrewMember();
                member.setSkill(skill);
                members.add(member);
                mapping.put(var, member);
            }

            List<TeamEntity> all = teamRepository.findAll();
            Tuples allPossibles = new Tuples(true);
            for (TeamEntity t : all) {
                for (int[] a : possibleResult(crew, t)) {
                    allPossibles.add(a);
                }
            }
            IntVar[] varToConstraintByTeam = new IntVar[varsByCrew.size()];
            varToConstraintByTeam = varsByCrew.toArray(varToConstraintByTeam);
            model.table(varToConstraintByTeam, allPossibles).post();

            Crew resultCrew = new Crew(members, crew.getName());
            result.add(resultCrew);
        }
        model.intVar("v0", 9, 45);

        IntVar[] solverVariablesTable = new IntVar[vars.size()];
        solverVariablesTable = vars.toArray(solverVariablesTable);
        model.allDifferentExcept0(solverVariablesTable).post();

        Solution solution = model.getSolver().findSolution();

        if (solution != null) {
            logger.info("A SOLUTION");
            for (IntVar integers : solverVariablesTable) {

                int currentId = solution.getIntVal(integers);
                if (currentId != nobody) {
                    TeammateEntity one = teammateRepository.getById((long) currentId);
                    mapping.get(integers).setTeammate(one);
                    logger.info((integers.getName() + " : " + one.getName()));
                } else {
                    logger.info(integers.getName() + " : " + "NOBODY");
                }
            }
        }

        return result;
    }

    public List<int[]> possibleResult(CrewSearchEntity crewSearch, TeamEntity team) {

        Model model = new Model("all permutation");
        List<IntVar> vars = new ArrayList<>();

        for (SkillEntity skill : crewSearch.getSkills()) {
            vars.add(model.intVar("Q_" + crewSearch.getName() + "_" + skill.getName(), possibleTeammateIdForSkill(skillRepository.getById(skill.getId()), team)));
        }

        IntVar[] solverVariablesTable = new IntVar[vars.size()];
        solverVariablesTable = vars.toArray(solverVariablesTable);

        model.allDifferent(solverVariablesTable).post();
        List<Solution> allSolutions = model.getSolver().findAllSolutions();
        List<int[]> result = new ArrayList<>();
        for (Solution s : allSolutions
        ) {
            int[] tuple = new int[solverVariablesTable.length];
            for (int i = 0; i < solverVariablesTable.length; i++) {
                int currentId = s.getIntVal(solverVariablesTable[i]);
                tuple[i] = currentId;
                if (currentId != nobody) {
                    TeammateEntity one = teammateRepository.getById((long) currentId);
                    logger.info("possibleResult " + (solverVariablesTable[i].getName() + " : " + one.getName()));
                } else {
                    logger.info("possibleResult " + solverVariablesTable[i].getName() + " : " + "NOBODY");
                }
            }
            result.add(tuple);
        }
        return result;
    }

    private int[] possibleTeammateIdForSkill(SkillEntity skillEntity, TeamEntity team) {
        logger.info("i m looking for skilled people to do  " + skillEntity.getName() + " in team " + team.getName());

        logger.info(skillEntity.getTeammatesHavingSkill() + "");
        int[] result = skillEntity.getTeammatesHavingSkill().stream().filter(x -> team.getTeammates().contains(x)).mapToInt(x -> x.getId().intValue()).toArray();
        logger.info("i found " + result.length + " people");
        if (result.length == 0) {
            result = new int[]{nobody};
        }
        return result;
    }


    private int[] possibleTeammateIdForSkill(SkillEntity skillEntity) {
        logger.info("i m looking for skilled people to do  " + skillEntity.getName());
        int[] result = skillEntity.getTeammatesHavingSkill().stream().mapToInt(x -> x.getId().intValue()).toArray();
        logger.info("i found " + result.length + " people");
        if (result.length == 0) {
            result = new int[]{nobody};
        }
        return result;
    }

}
