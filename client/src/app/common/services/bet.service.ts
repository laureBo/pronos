import { ApiUtils } from 'src/app/common/services/api.utils';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BetInput } from '../model/bet.input.model';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class BetService {
  constructor(private http: HttpClient) {}

  public createNewBet$(newBet: BetInput): Observable<string> {
    return this.http.post<string>(ApiUtils.ROOT_URL + 'paris/save', newBet);
  }
}
