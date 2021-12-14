import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {Skill} from "./teams/Skill";
import {catchError} from "rxjs/operators";
import {Crew} from "./search/Crew";
import {CrewSearch} from "./search/CrewSearch";
import {Search} from "./search/Search";

@Injectable({
  providedIn: 'root'
})
export class SearchService {


  private searchUrl = 'http://localhost:8082/search/';  // URL to web api
  private searchsUrl = 'http://localhost:8082/searchs/';  // URL to web api
  constructor(private http: HttpClient) { }

  search(searchs:CrewSearch[]): Observable<Crew[]> {
    return this.http.post<Crew[]>(this.searchUrl+"execute",searchs).pipe(
      catchError(this.handleError<Crew[]>('search', []))
    );
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      //this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  save(search: Search): Observable<Search> {
    return this.http.post<Search>(this.searchUrl,search).pipe(
      catchError(this.handleError<Search>('search', undefined))
    );
  }

  getSearchs(): Observable<Search[]> {
    return this.http.get<Search[]>(this.searchsUrl).pipe(
      catchError(this.handleError<Search[]>('getSearchs', []))
    );
  }

  getSearch(id: number) : Observable<Search>{
    return this.http.get<Search>(this.searchUrl+id+"/").pipe(
      catchError(this.handleError<Search>('getSearch', undefined))
    );
  }
}
