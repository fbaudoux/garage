import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {Team} from "./teams/Team";
import {catchError} from "rxjs/operators";
import {Skill} from "./teams/Skill";

@Injectable({
  providedIn: 'root'
})
export class SkillService {

  private skillsUrl = 'http://localhost:8082/skills/';  // URL to web api
  constructor(private http: HttpClient) { }

  getSkills(): Observable<Skill[]> {
    return this.http.get<Skill[]>(this.skillsUrl).pipe(
      catchError(this.handleError<Skill[]>('getSkills', []))
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

  getSkill(id: number) : Observable<Skill>{
    return this.http.get<Skill>(this.skillsUrl+id).pipe(
      catchError(this.handleError<Skill>('getSkills', undefined))
    );
  }

  updateSkill(skill: Skill | undefined) : Observable<Skill> {
    return this.http.post<Skill>(this.skillsUrl, skill).pipe(
      catchError(this.handleError<Skill>('updateSkill', undefined))
    );
  }


}
