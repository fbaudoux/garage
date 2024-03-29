import { Component, OnInit, Input } from '@angular/core';
import { Team } from '../teams/Team';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { TeamService } from '../team.service';



@Component({
  selector: 'app-team-detail',
  templateUrl: './team-detail.component.html',
  styleUrls: ['./team-detail.component.css']
})
export class TeamDetailComponent implements OnInit {

  @Input() team?: Team;

  constructor(private route: ActivatedRoute,
  private teamService: TeamService,
  private location: Location) { }

  ngOnInit(): void {
    this.getTeam();
  }

  getTeam(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if(id) {
      this.teamService.getTeam(id)
        .subscribe(team => this.team = team);
    }
    else{
      this.team = {id: 0, name: "", teammates: []};
    }
  }

  goBack(): void {
    this.location.back();
  }

  saveTeam() {
    this.teamService.updateTeam(this.team).subscribe(team => this.team = team );
  }

  deleteTeam() {
    if(this.team) {
      console.log("delete");
      this.teamService.deleteTeam(this.team).subscribe(result => this.location.back());
    }
  }
}
