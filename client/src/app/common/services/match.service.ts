import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Match } from 'src/app/components/match/match.models';
import { MajScore } from '../model/majScore.input.model';
import { MatchInput } from '../model/match.input.model';
import { MatchOutput } from '../model/match.output.model';

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
    return this.http.put<string>(
      this.ROOT_URL + 'matchs/' + idMatch + '/maj-score',
      majScore
    );
  }

  //delete
  public deleteMatch$(match: MatchInput): Observable<String> {
    return this.http.delete<string>(this.ROOT_URL + 'matchs/' + match.id);
  }
}
