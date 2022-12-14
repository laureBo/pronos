import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Match } from 'src/app/components/match/match.models';
import { MajScore } from '../model/majScore.input.model';

@Injectable({
  providedIn: 'root',
})
export class MatchService {
  private readonly ROOT_URL = 'http://localhost:8080/';
  optionRequete = {
    headers: new HttpHeaders({
      'Access-Control-Allow-Origin': 'http://localhost:4200',
    }),
  };

  constructor(private http: HttpClient) {}

  //create
  public createNewMatch$(
    newMatch: Match,
    idSession: number
  ): Observable<string> {
    return this.http.post<string>(
      this.ROOT_URL + 'sessions/' + idSession + '/ajouter-match',
      newMatch
    );
  }

  //update
  public updateScoreMatch$(
    majScore: MajScore,
    idMatch: number
  ): Observable<String> {
    return this.http.post<string>(
      this.ROOT_URL + 'matchs/' + idMatch + '/maj-score',
      majScore
    );
  }

  //delete
  public deleteMatch$(idSession: number, match: Match): Observable<String> {
    return this.http.post<string>(
      this.ROOT_URL + 'sessions/' + idSession + '/supprimer-match',
      match
    );
  }
}
