import {Component, Input, OnInit} from '@angular/core';
import {Skill} from "../teams/Skill";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {SkillService} from "../skill.service";

@Component({
  selector: 'app-skill-detail',
  templateUrl: './skill-detail.component.html',
  styleUrls: ['./skill-detail.component.css']
})
export class SkillDetailComponent implements OnInit {

  @Input() skill?: Skill;

  constructor(private route: ActivatedRoute,
              private location: Location,
              private skillService : SkillService,
              ) { }

  ngOnInit(): void {
    this.getSkill();
  }

  getSkill(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if(id) {
      this.skillService.getSkill(id)
        .subscribe(skill => this.skill = skill);
    }
    else{
      this.skill = {id:0 , name:""};
    }
  }

  goBack(): void {
    this.location.back();
  }
  updateSkill() {
    this.skillService.updateSkill(this.skill).subscribe(result =>
      {
        console.log(result);
      }
    );
  }

  deleteSkill() {
    if (this.skill) {
      this.skillService.deleteSkill(this.skill).subscribe(result => {
          this.goBack();
        }
      );
    }
  }
}
