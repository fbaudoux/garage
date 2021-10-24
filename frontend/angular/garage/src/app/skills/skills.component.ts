import { Component, OnInit } from '@angular/core';
import {Team} from "../teams/Team";
import {TeamService} from "../team.service";
import {Skill} from "../teams/Skill";
import {SkillService} from "../skill.service";

@Component({
  selector: 'app-skills',
  templateUrl: './skills.component.html',
  styleUrls: ['./skills.component.css']
})
export class SkillsComponent implements OnInit {

  skills:Skill[] = [];
  constructor(private skillService: SkillService) { }

  ngOnInit(): void {
    this.getSkills();
  }

  getSkills(): void {
    this.skillService.getSkills().subscribe(skills => this.skills = skills);
  }

}
