import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SessionInput, SessionLightInput } from '../model/session.input.model';
import { Observable } from 'rxjs';
import { SessionOutput } from '../model/session.output.model';
import { BetInput } from '../model/bet.input.model';

@Injectable({
  providedIn: 'root',
})
export class BetService {
  private readonly ROOT_URL = 'http://localhost:8080/';
  optionRequete = {
    headers: new HttpHeaders({
      'Access-Control-Allow-Origin': 'http://localhost:4200',
    }),
  };

  constructor(private http: HttpClient) {}

  public createNewBet$(newBet: BetInput): Observable<string> {
    return this.http.post<string>(this.ROOT_URL + 'paris/save', newBet);
  }
}
