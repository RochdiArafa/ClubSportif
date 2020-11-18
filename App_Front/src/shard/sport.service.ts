import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Router } from '@angular/router';
import {catchError, map} from 'rxjs/operators';


@Injectable()
export class SportService {

  constructor(private http: Http, public router: Router) {}

   getFootBallTeams(typesport:String) {
     return this.http.get(`http://localhost:8080/api/sport/SportTeams/`+typesport).pipe(map((res: any) => {
      const data = JSON.parse(res._body);
      return data.results.bindings
    }), catchError(this.handleError));

  }

  findTeam(teamName:String) {
    return this.http.get(`http://localhost:8080/api/sport/teamsDetails/`+ teamName).pipe(map((res: any) => {
     const data = JSON.parse(res._body);
     return data.results.bindings
   }), catchError(this.handleError));

 }



  getAllSports() {
    return this.http.get(`http://localhost:8080/api/sport/SportsType`).pipe(map((res: any) => {
      const data = JSON.parse(res._body);
      return data.results.bindings
    }), catchError(this.handleError));
  }

  findPlayer(nom: string, prenom: string) {
    return this.http.get(`http://localhost:8080/api/sport/findPlayer/` + nom + `/` + prenom).pipe(map((res: any) => {
      const data = JSON.parse(res._body);
      return data.results.bindings
    }), catchError(this.handleError));
  }

  getAllPlayers(teamName : String) {
    return this.http.get(`http://localhost:8080/api/sport/playersByTeam/` + teamName)
    .pipe(map((res: any) => {
      const data = JSON.parse(res._body);
      return data.results.bindings
    }), catchError(this.handleError));
  }

  getMatchNotPlayed() {
    return this.http.get(`http://localhost:8080/api/sport/MatchNotPlayed`)
    .pipe(map((res: any) => {
      const data = JSON.parse(res._body);
      return data.results.bindings
    }), catchError(this.handleError));
  }

  getAllEndedMatch() {
    return this.http.get(`http://localhost:8080/api/sport/endedmatch`)
    .pipe(map((res: any) => {
      const data = JSON.parse(res._body);
      return data.results.bindings
    }), catchError(this.handleError));
  }

  private handleError(error: any): Promise<any> {
    console.error('Une erreur est survenue : ', error);
    return Promise.reject(error.message || error);
  }
}
