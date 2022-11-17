import {
  HttpClient,
  HttpClientModule,
  HttpHeaders,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserInput } from '../model/user.input.model';
import { UserOutput } from '../model/user.output.model';
@Injectable({
  providedIn: 'root',
})
export class ApiService {
  private readonly ROOT_URL = 'http://localhost:8080/';

  optionRequete = {
    headers: new HttpHeaders({
      'Access-Control-Allow-Origin': '*',
    }),
  };

  constructor(private http: HttpClient) {}

  public createNewUser$(user: UserOutput): Observable<string> {
    return this.http.post<string>(this.ROOT_URL + 'utilisateurs/', user);
  }

  public getUser$(pseudo: string): Observable<UserInput> {
    return this.http.get<UserInput>(this.ROOT_URL + 'utilisateurs/' + pseudo);
  }
}
