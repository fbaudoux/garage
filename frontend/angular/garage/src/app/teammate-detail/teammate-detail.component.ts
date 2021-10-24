import { Component, OnInit, Input } from '@angular/core';
import { Teammate } from '../teams/Teammate';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { TeammateService } from '../teammate.service';
import {Team} from "../teams/Team";
import {TeamService} from "../team.service";
import {SkillService} from "../skill.service";
import {Skill} from "../teams/Skill";

@Component({
  selector: 'app-teammate-detail',
  templateUrl: './teammate-detail.component.html',
  styleUrls: ['./teammate-detail.component.css']
})
export class TeammateDetailComponent implements OnInit {

  @Input() teammate?: Teammate;
  skills : Skill[] = [];

  constructor(private route: ActivatedRoute,
              private teammateService: TeammateService,
              private location: Location,
              private skillService : SkillService) { }

  ngOnInit(): void {
    this.getTeammate();
    this.getSkills();
  }

  getTeammate(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.teammateService.getTeammate(id)
      .subscribe(teammate => this.teammate = teammate);
  }

  goBack(): void {
    this.location.back();
  }

  private getSkills() {
    this.skillService.getSkills().subscribe(skills => this.skills = skills);
  }

  onSkillsChange() {
    console.log("onSkillsCHnage");
    this.teammateService.updateTeammate(this.teammate).subscribe(result => console.log(result) );
  }
}
