import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SessionInput, SessionLightInput } from '../model/session.input.model';
import { Observable } from 'rxjs';
import { SessionOutput } from '../model/session.output.model';
import { Match } from 'src/app/components/match/match.models';
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

  public createNewMatch$(
    newMatch: Match,
    idSession: number
  ): Observable<string> {
    return this.http.post<string>(
      this.ROOT_URL + 'sessions/' + idSession + '/ajouter-match',
      newMatch
    );
  }
}
