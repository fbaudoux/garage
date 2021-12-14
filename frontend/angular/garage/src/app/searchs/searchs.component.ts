import { Component, OnInit } from '@angular/core';
import {Skill} from "../teams/Skill";
import {SkillService} from "../skill.service";
import {Search} from "../search/Search";
import {SearchService} from "../search.service";

@Component({
  selector: 'app-searchs',
  templateUrl: './searchs.component.html',
  styleUrls: ['./searchs.component.css']
})
export class SearchsComponent implements OnInit {

  searchs:Search[] = [];
  constructor(private searchService: SearchService) { }


  ngOnInit(): void {
    this.getSearchs();
  }

  getSearchs(): void {
    this.searchService.getSearchs().subscribe(searchs => this.searchs = searchs);
  }

}
