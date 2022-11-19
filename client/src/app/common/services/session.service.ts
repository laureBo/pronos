import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SessionInput, SessionLightInput } from '../model/session.input.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class SessionService {
  private readonly ROOT_URL = 'http://localhost:8080/';
  optionRequete = {
    headers: new HttpHeaders({
      'Access-Control-Allow-Origin': '*',
    }),
  };

  constructor(private http: HttpClient) {}

  public getSessionsByUser$(pseudo: string): Observable<SessionLightInput[]> {
    return this.http.get<SessionLightInput[]>(
      this.ROOT_URL + 'utilisateurs/' + pseudo + '/sessions'
    );
  }
}
