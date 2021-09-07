package fr.crew.garage.domain.search;

import fr.crew.garage.domain.skill.entity.SkillEntity;
import fr.crew.garage.domain.team.entity.TeammateEntity;
import fr.crew.garage.domain.team.repository.TeammateRepository;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.variables.IntVar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchDomainService {

    Logger logger = LoggerFactory.getLogger(SearchDomainService.class);

    @Autowired
    TeammateRepository teammateRepository;

    public void search(List<Crew> crews) {

        Model model = new Model("team search problem");
        List<IntVar> vars = new ArrayList<>();
        for (Crew crew : crews) {
            logger.info("The crew " + crew.getName() + " needs " + crew.getSkills().size() + " members");
            for (SkillEntity skill : crew.getSkills()) {
                // On crée une variable pour trouver chaque membre de l equipage
                vars.add(model.intVar("Q_" + crew.getName() + "_" + skill.getName(), possibleTeammateIdForSkill(skill)));
            }
        }
        model.intVar("v0", 9, 45);

        IntVar[] solverVariablesTable = new IntVar[vars.size()];
        solverVariablesTable = vars.toArray(solverVariablesTable);
        model.allDifferent(solverVariablesTable).post();
        Solution solution = model.getSolver().findSolution();

        for (int i = 0; i < solverVariablesTable.length; i++) {

            int currentId = solution.getIntVal(solverVariablesTable[i]);
            if (currentId != -1) {
                TeammateEntity one = teammateRepository.getOne((long) currentId);
                logger.info((solverVariablesTable[i].getName() + " : " + one.getName()));
            } else {
                logger.info(solverVariablesTable[i].getName() + " : " + "NOBODY");
            }
        }
    }


    private int[] possibleTeammateIdForSkill(SkillEntity skillEntity) {
        logger.info("i m looking for skilled people to do  " + skillEntity.getName());
        int[] result = skillEntity.getTeammatesHavingSkill().stream().mapToInt(x -> x.getId().intValue()).toArray();
        logger.info("i found " + result.length + " people");
        if (result.length == 0) {
            result = new int[] {-1};
        }
        return result;
    }

}
