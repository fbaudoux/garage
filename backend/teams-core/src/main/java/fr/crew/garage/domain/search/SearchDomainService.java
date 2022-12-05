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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class SearchDomainService {


    final
    TeammateRepository teammateRepository;

    final
    TeamRepository teamRepository;

    final
    SkillRepository skillRepository;


    private final int nobody = 0;

    public SearchDomainService(TeammateRepository teammateRepository, TeamRepository teamRepository, SkillRepository skillRepository) {
        this.teammateRepository = teammateRepository;
        this.teamRepository = teamRepository;
        this.skillRepository = skillRepository;

    }

    public Collection<Crew> search(List<CrewSearchEntity> crewSearches) {

        List<TeamEntity> allTeams = teamRepository.findAll();

        Collection<Crew> result = new ArrayList<>();

        //Le model est un objet du framework choco qui represente le problème qu'il faut résoudre
        Model model = new Model("team search problem");

        //Les inconnues du problème que nous cherchons à résoudre. Nous en aurons autant que de CrewMember recherchés dans nos equipages
        // Ce sont bien des CrewMember que l on va rechercher mais le solveur ne peut nous retourner que des Int donc
        // on va lui faire retourner les identifiants des Teammates
        List<IntVar> unknowns = new ArrayList<>();


        //On garde donc la correspondance entre la variable que va résoudre le solver et le CrewMember
        HashMap<IntVar, CrewMember> mappingSolverVariableAndAssociatedCrewMember = new HashMap<>();

        //Pour chaque equipage recherché
        for (CrewSearchEntity crewSearch : crewSearches) {

            // La solution trouvée sera un ensemble de CrewMember
            List<CrewMember> members = new ArrayList<>();

            // On va garder la liste des inconnues du problème qui correspondent uniquement à cet équipage
            List<IntVar> unknownsByCrew = new ArrayList<>();

            // Pour chaque compétence nécessaire dans notre équipage
            for (SkillEntity skill : crewSearch.getSkills()) {
                // On crée une variable pour trouver chaque membre de l equipage
                // On lui donne un nom et un domaine de valeur. Le domaine de valeur correspond à tous les identifiants des teammates qui possèdent la compétence recherchée
                IntVar searchedTeammateId = model.intVar("Q_" + crewSearch.getName() + "_" + skill.getName(), possibleTeammateIdForSkill(skillRepository.getById(skill.getId())));
                unknowns.add(searchedTeammateId);
                unknownsByCrew.add(searchedTeammateId);

                // A cette inconnue , on associe une reponse qui sera un CrewMember à qui on viendra associé un teammates quand le solver aura résolu le problème
                CrewMember member = new CrewMember();
                member.setSkill(skill);

                // On ajoute ce membre d equipage a la solution mais il est pour le moment incomplet car il n a pas de teammate associé
                members.add(member);

                // On garde le lien entre l inconnue a resoudre et le member qui devra être complété quand la solution aura été trouvée
                mappingSolverVariableAndAssociatedCrewMember.put(searchedTeammateId, member);
            }

            // On doit donner au solver l ensemble des solutions possibles
            Tuples allPossibles = new Tuples(true);
            // Comme on veut selectionner les membres d equipages dans la même team , on ne selectionne que des solutions
            // au sein d'une même team
            for (TeamEntity t : allTeams) {
                for (int[] a : possibleResultForACrewSearchUsingOneTeam(crewSearch, t)) {
                    allPossibles.add(a);
                }
            }

            // on passe au modèle les différentes inconnues et les contraintes associées
            IntVar[] varToConstraintByTeam = new IntVar[unknownsByCrew.size()];
            varToConstraintByTeam = unknownsByCrew.toArray(varToConstraintByTeam);
            model.table(varToConstraintByTeam, allPossibles).post();

            // On place l objet resultat imcomplet dans l objet qui sera retourné
            Crew resultCrew = new Crew(members, crewSearch.getName());
            result.add(resultCrew);
        }
        model.intVar("v0", 9, 45);

        // On passe au model une contraintes sur toutes les variables qui indique qu'on ne peut pas résoudre 2 inconnues différentes avec une même réponse
        IntVar[] solverVariablesTable = new IntVar[unknowns.size()];
        solverVariablesTable = unknowns.toArray(solverVariablesTable);
        model.allDifferentExcept0(solverVariablesTable).post();

        Solution solution = model.getSolver().findSolution();

        if (solution != null) {
            for (IntVar integers : solverVariablesTable) {
                int currentId = solution.getIntVal(integers);
                if (currentId != nobody) {
                    TeammateEntity one = teammateRepository.getById((long) currentId);
                    mappingSolverVariableAndAssociatedCrewMember.get(integers).setTeammate(one);
                }
            }
        }

        return result;
    }

    public List<int[]> possibleResultForACrewSearchUsingOneTeam(CrewSearchEntity crewSearch, TeamEntity team) {

        Model model = new Model("all permutation");
        List<IntVar> vars = new ArrayList<>();

        for (SkillEntity skill : crewSearch.getSkills()) {
            int[] whoHaveTheSkillInThisTeam = possibleTeammateIdForSkill(skillRepository.getById(skill.getId()), team);
            if (whoHaveTheSkillInThisTeam.length != 0) {
                vars.add(model.intVar("Q_" + crewSearch.getName() + "_" + skill.getName(), whoHaveTheSkillInThisTeam));
            } else {
                return new ArrayList<>();
            }
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
            }
            result.add(tuple);
        }
        return result;
    }

    private int[] possibleTeammateIdForSkill(SkillEntity skillEntity, TeamEntity team) {
        int[] result = team.getTeammates().stream().filter(mate -> mate.getSkills().contains(skillEntity)).mapToInt(x -> x.getId().intValue()).toArray();
        return result;
    }


    private int[] possibleTeammateIdForSkill(SkillEntity skillEntity) {
        int[] result = teammateRepository.findAll().stream().mapToInt(x -> x.getId().intValue()).toArray();
        return result;
    }

}
