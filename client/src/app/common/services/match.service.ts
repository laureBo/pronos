import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Match } from 'src/app/components/match/match.models';
import { MajScore } from '../model/majScore.input.model';
import { MatchInput } from '../model/match.input.model';
import { MatchOutput } from '../model/match.output.model';
import { ApiUtils } from './api.utils';

@Injectable({
  providedIn: 'root',
})
export class MatchService {
  constructor(private http: HttpClient) {}

  //create
  public createNewMatch$(
    newMatch: Match,
    idSession: number
  ): Observable<string> {
    return this.http.post<string>(
      ApiUtils.ROOT_URL + 'sessions/' + idSession + '/ajouter-match',
      newMatch
    );
  }

  //update
  public updateScoreMatch$(
    majScore: MajScore,
    idMatch: number
  ): Observable<string> {
    return this.http.put<string>(
      ApiUtils.ROOT_URL + 'matchs/' + idMatch + '/maj-score',
      majScore
    );
  }

  //delete
  public deleteMatch$(match: MatchInput): Observable<string> {
    return this.http.delete<string>(ApiUtils.ROOT_URL + 'matchs/' + match.id);
  }
}
