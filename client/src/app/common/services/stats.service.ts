import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StatsInput } from '../model/stat.input.model';
import { StatsOutput } from '../model/stats.output.model';

@Injectable({
  providedIn: 'root',
})
export class SessionService {
  private readonly ROOT_URL = 'http://localhost:8080/';
  optionRequete = {
    headers: new HttpHeaders({
      'Access-Control-Allow-Origin': 'http://localhost:4200',
    }),
  };

  constructor(private http: HttpClient) {}

  //getStatByNomSession
  public getStatsBySession(statInput: StatsInput) {
    this.http.get<StatsOutput>(this.ROOT_URL + '/stats/bySession');
  }
}
