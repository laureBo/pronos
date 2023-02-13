import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StatsInput } from '../model/stat.input.model';
import { StatsOutput } from '../model/stats.output.model';
import { ApiUtils } from './api.utils';

@Injectable({
  providedIn: 'root',
})
export class SessionService {
  constructor(private http: HttpClient) {}

  //getStatByNomSession
  public getStatsBySession(statInput: StatsInput) {
    this.http.get<StatsOutput>(ApiUtils.ROOT_URL + '/stats/bySession');
  }
}
