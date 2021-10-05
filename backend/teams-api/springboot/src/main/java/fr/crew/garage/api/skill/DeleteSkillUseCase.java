package fr.crew.garage.api.skill;

import fr.crew.garage.api.skill.dto.SkillDTO;
import fr.crew.garage.domain.skill.entity.SkillEntity;
import fr.crew.garage.domain.skill.repository.SkillRepository;
import fr.crew.garage.domain.team.entity.TeammateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class DeleteSkillUseCase {

    @Autowired
    SkillRepository skillRepository;


    @Transactional
    public void execute(SkillDTO skillDTO) {

        SkillEntity skillEntity = skillRepository.getById(skillDTO.getId());
        List<TeammateEntity> teammatesHavingSkill = skillEntity.getTeammatesHavingSkill();
        for (TeammateEntity t : teammatesHavingSkill) {
            t.justRemoveSkill(skillEntity);
        }
        skillEntity.removeSkillToAllTeammates();
        skillRepository.deleteById(skillDTO.getId());
    }
}
