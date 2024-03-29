import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SessionInput, SessionLightInput } from '../model/session.input.model';
import { Observable } from 'rxjs';
import { SessionOutput } from '../model/session.output.model';
import { MatchInput } from '../model/match.input.model';
import { PariDetail } from '../model/pariDetail.models';
import { StatsOutput } from '../model/stats.output.model';
import { ApiUtils } from './api.utils';
import { Info } from '../model/info.model';

@Injectable({
  providedIn: 'root',
})
export class SessionService {
  constructor(private http: HttpClient) {}

  //obtenir ttes les session d un user par son pseudo en petit format
  public getSessionsLightByUser$(
    pseudo: string
  ): Observable<SessionLightInput[]> {
    return this.http.get<SessionLightInput[]>(
      ApiUtils.ROOT_URL + 'utilisateurs/' + pseudo + '/sessions'
    );
  }

  //obtenir une session user par un id session
  public getSessionById$(id: number): Observable<SessionInput> {
    return this.http.get<SessionInput>(ApiUtils.ROOT_URL + 'sessions/' + id);
  }

  //créer une nouvelle session
  public createNewSession$(newSession: SessionOutput): Observable<Info> {
    return this.http.post<Info>(ApiUtils.ROOT_URL + 'sessions/', newSession);
  }

  //supprimer une session
  public deleteSession$(idSession: number): Observable<string> {
    return this.http.post<string>(
      ApiUtils.ROOT_URL + 'sessions/' + idSession + '/supprimer-session',
      idSession
    );
  }

  //récuperer list de matchs pour une session
  public getMatchsByIdSession$(idSession: number): Observable<MatchInput[]> {
    return this.http.get<MatchInput[]>(
      ApiUtils.ROOT_URL + 'sessions/' + idSession + '/matchs'
    );
  }

  //recuperer une liste de pariDetail
  public getParisDetailByPseudoAndSession$(
    idSession: number,
    pseudo: string
  ): Observable<PariDetail[]> {
    return this.http.get<PariDetail[]>(
      ApiUtils.ROOT_URL +
        'sessions/' +
        idSession +
        '/utilisateur/' +
        pseudo +
        '/paris'
    );
  }

  //recuperer la liste de tous les users d une session
  public getAllusersBySession$(idSession: number): Observable<string[]> {
    return this.http.get<string[]>(
      ApiUtils.ROOT_URL + 'sessions/' + idSession + '/utilisateurs'
    );
  }

  //afficher le classement d'une session
  // public getPourcentRankingBySession$(
  // idSession: number
  //): Observable<{ [key: string]: number }> {
  // return this.http.get<{ [key: string]: number }>(
  //   this.ROOT_URL + 'sessions/' + idSession + '/utilisateurs/ranking'
  // );
  // }

  //afficher toutes les stats des utilisateurs d'une session et le classement
  public getAllStatsAndRankingBySession$(
    idSession: number
  ): Observable<StatsOutput[]> {
    return this.http.get<StatsOutput[]>(
      ApiUtils.ROOT_URL + 'sessions/' + idSession + '/utilisateurs/stats'
    );
  }
}
