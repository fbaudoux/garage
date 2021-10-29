import { Component, OnInit } from '@angular/core';
import {CrewSearch} from "./CrewSearch";
import {Skill} from "../teams/Skill";
import {SkillService} from "../skill.service";
import {SearchService} from "../search.service";
import {Crew} from "./Crew";
import {FormControl} from "@angular/forms";
import {Search} from "./Search";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  search : Search = {name: "default search", searches: []};
  searchResult : Crew[] = [];

  skills:Skill[] = [];
  constructor(private skillService: SkillService,private searchService: SearchService) { }

  ngOnInit(): void {
    this.addCrewSearch();
    this.getSkills();
  }

  getSkills(): void {
    this.skillService.getSkills().subscribe(skills => this.skills = skills);
  }

  addCrewSearch() {
    let searchName = "Crew#" + (this.search.searches.length+1);
    let newSearch:CrewSearch = {name:searchName , skills:[]};
    this.search.searches.push(newSearch);
  }


  removeSearch(search: CrewSearch) {
    const index: number = this.search.searches.indexOf(search);
    this.search.searches.splice(index, 1);
  }

  save() {
    this.searchService.save(this.search).subscribe(result => this.search = result);
  }

  load() {
  }

  executeSearch() {
    this.searchService.search(this.search.searches).subscribe(crews => this.searchResult = crews)

  }
}
