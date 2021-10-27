import { Injectable } from '@angular/core';
import {Observable, of} from "rxjs";
import {catchError} from "rxjs/operators";
import {Teammate} from "./teams/Teammate";
import {HttpClient} from "@angular/common/http";
import {Team} from "./teams/Team";

@Injectable({
  providedIn: 'root'
})
export class TeammateService {

  private teammatesUrl = 'http://localhost:8082/teammates/';  // URL to web api
  constructor(private http: HttpClient) { }

  getTeammate(id: number): Observable<Teammate> {
    // For now, assume that a hero with the specified `id` always exists.
    // Error handling will be added in the next step of the tutorial.

    const url = `${this.teammatesUrl}${id}/`;

    return this.http.get<Teammate>(url).pipe(
      catchError(this.handleError<Teammate>('getTeammate', undefined))
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

  updateTeammate(teammate: Teammate | undefined): Observable<Teammate>{
    return  this.http.put<Teammate>(this.teammatesUrl,teammate).pipe(
      catchError(this.handleError<Teammate>('updateTeammate', undefined))
    );
  }

  deleteTeammate(teammate: Teammate) {
    return  this.http.delete<Teammate>(this.teammatesUrl+teammate.id).pipe(
      catchError(this.handleError<Teammate>('deleteTeammate', undefined))
    );
  }
}
