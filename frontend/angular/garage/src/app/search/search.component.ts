import {Component, Input, OnInit} from '@angular/core';
import {CrewSearch} from "./CrewSearch";
import {Skill} from "../teams/Skill";
import {SkillService} from "../skill.service";
import {SearchService} from "../search.service";
import {Crew} from "./Crew";
import {FormControl} from "@angular/forms";
import {Search} from "./Search";
import {ActivatedRoute} from "@angular/router";
import {Teammate} from "../teams/Teammate";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {


  @Input() search?: Search;
  searchResult : Crew[] = [];
  skills:Skill[] = [];
  compareFn(mate1: CrewSearch, mate2: CrewSearch) {
    return mate1 && mate2 ? mate1.name === mate2.name : mate1 === mate2;
  }


  constructor(private skillService: SkillService,private searchService: SearchService,private route: ActivatedRoute) { }

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if(id) {
      this.searchService.getSearch(id)
        .subscribe(search => {
          this.search = search ;
        });
    }
    else{
      this.search = {id: 0, name: "default search", searches: []};
      this.addCrewSearch();
    }

    this.getSkills();
  }

  getSkills(): void {
    this.skillService.getSkills().subscribe(skills => this.skills = skills);
  }

  addCrewSearch() {
    if (this.search) {
      let searchName = "Crew#" + (this.search.searches.length + 1);
      let newSearch: CrewSearch = {name: searchName, skills: []};
      this.search.searches.push(newSearch);
    }
  }


  removeSearch(search: CrewSearch) {
    if(this.search) {
      const index: number = this.search.searches.indexOf(search);
      this.search.searches.splice(index, 1);
    }
  }

  save() {
    if(this.search) {
      this.searchService.save(this.search).subscribe(result => this.search = result);
    }
  }

  executeSearch() {
    if(this.search) {
      this.searchService.search(this.search.searches).subscribe(crews => this.searchResult = crews)
    }

  }
}
