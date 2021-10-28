import { Component, OnInit, Input } from '@angular/core';
import { Teammate } from '../teams/Teammate';
import {ActivatedRoute, Router} from '@angular/router';
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
  @Input() team?: Team;

  skills : Skill[] = [];
  compareFn(mate1: Teammate, mate2: Teammate) {
    return mate1 && mate2 ? mate1.id === mate2.id : mate1 === mate2;
  }

  constructor(private route: ActivatedRoute,
              private router: Router,
              private teammateService: TeammateService,
              private location: Location,
              private skillService : SkillService,
              private teamService : TeamService) { }

  ngOnInit(): void {
    this.getTeam();
    this.getTeammate();
    this.getSkills();
  }

  getTeammate(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if(id) {
      this.teammateService.getTeammate(id)
        .subscribe(teammate => this.teammate = teammate);
    }
    else{
      this.teammate = {id:0 , name:"" , skills:[]};
    }
  }

  goBack(): void {
    if(this.team){
      this.router.navigate(["/detail/"+this.team.id]);
    }
    else{
      this.router.navigate(['teams/']);
    }
  }

  private getSkills() {
    this.skillService.getSkills().subscribe(skills => this.skills = skills);
  }

  updateTeammate() {
    this.teammateService.updateTeammate(this.teammate).subscribe(teammate =>
    {
      this.teammate = teammate;
      if (this.team){
        if (this.teammate) {
          this.team.teammates.push(this.teammate);
        }
        this.teamService.updateTeam(this.team).subscribe( result =>this.goBack());
      }
    });
  }

  private getTeam() {
    const id = Number(this.route.snapshot.paramMap.get('teamid'));
    if(id) {
      this.teamService.getTeam(id)
        .subscribe(team => this.team = team);
    }
  }

  delete() {
    if(this.teammate) {
      this.teammateService.deleteTeammate(this.teammate).subscribe(result => this.goBack());
    }
  }
}
