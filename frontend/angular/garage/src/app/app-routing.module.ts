import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TeamsComponent } from './teams/teams.component';
import { TeamDetailComponent } from './team-detail/team-detail.component';
import {TeammateDetailComponent} from "./teammate-detail/teammate-detail.component";
import {SkillsComponent} from "./skills/skills.component";
import {SearchComponent} from "./search/search.component";

const routes: Routes = [
  { path: 'teams', component: TeamsComponent },
  { path: 'search', component: SearchComponent },
  { path: 'skills', component: SkillsComponent },
  { path: 'detail/:id', component: TeamDetailComponent },
  { path: 'teammate/:id', component: TeammateDetailComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
