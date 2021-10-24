import { Component, OnInit } from '@angular/core';
import {CrewSearch} from "./CrewSearch";
import {Skill} from "../teams/Skill";
import {SkillService} from "../skill.service";
import {SearchService} from "../search.service";
import {Crew} from "./Crew";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  searchs : CrewSearch[] = [];
  searchResult : Crew[] = [];

  skills:Skill[] = [];
  constructor(private skillService: SkillService,private searcService: SearchService) { }

  ngOnInit(): void {
    this.addSearch();
    this.getSkills();
  }

  getSkills(): void {
    this.skillService.getSkills().subscribe(skills => this.skills = skills);
  }

  addSearch() {
    let searchName = "search part#" + (this.searchs.length+1);
    let newSearch:CrewSearch = {name:searchName , skills:[]};
    this.searchs.push(newSearch);
  }

  search() {
      this.searcService.search(this.searchs).subscribe(crews => this.searchResult = crews)
  }
}
