import { AuthenticationService } from 'src/app/common/services/authentication.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserInput } from '../model/user.input.model';
import { UserOutput } from '../model/user.output.model';
import { ApiUtils } from './api.utils';
@Injectable({
  providedIn: 'root',
})
export class UtilisateurService {
  constructor(
    private http: HttpClient,
    private _authenticartionService: AuthenticationService
  ) {}

  public createNewUser$(user: UserOutput): Observable<string> {
    return this.http.post<string>(ApiUtils.ROOT_URL + 'utilisateurs/', user);
  }

  public updateUser$(user: UserOutput): Observable<string> {
    return this.http.post<string>(
      ApiUtils.ROOT_URL + 'utilisateurs/update',
      user
    );
  }

  public getUser$(pseudo: string): Observable<UserInput> {
    return this.http.get<UserInput>(
      ApiUtils.ROOT_URL + 'utilisateurs/' + pseudo
    );
  }

  public connectUser$(pseudo: string, password: string): Observable<UserInput> {
    this._authenticartionService.setAuthData(pseudo, password);
    return this.http.get<UserInput>(ApiUtils.ROOT_URL + 'utilisateurs/');
  }
}
