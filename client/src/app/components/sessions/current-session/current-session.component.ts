import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatchInput } from 'src/app/common/model/match.input.model';
import { SessionInput } from 'src/app/common/model/session.input.model';
import { SessionService } from 'src/app/common/services/session.service';
import { SessionMapperService } from '../session-mapper.service';
import { SessionSummaryComplete } from '../session-summary/session-summary.model';

@Component({
  selector: 'app-current-session',
  templateUrl: './current-session.component.html',
  styleUrls: ['./current-session.component.scss'],
})
export class CurrentSessionComponent implements OnInit {
  inputSessionSummaryComplete!: SessionSummaryComplete;
  matchs: MatchInput[];

  isLoaded = false;
  isEmpty = false;

  constructor(
    private _router: Router,
    private _route: ActivatedRoute,
    private _sessionService: SessionService,
    private _sessionMapper: SessionMapperService
  ) {}

  ngOnInit(): void {
    //on recupere l id selectionné dans l url
    const idSession: number = Number(
      this._route.snapshot.paramMap.get('id') as string
    );
    console.log(idSession);

    //on appelle la fct qui renvoit l objet complet (avec ses listes)
    this._sessionService
      .getSessionById$(idSession)
      .subscribe((inputSessionComplete: SessionInput) => {
        this.inputSessionSummaryComplete =
          this._sessionMapper.mapInputCompleteToComponent(inputSessionComplete);
        //parametre permettant d afficher que lorsque l objet est chargé grace au ngif
        this.isLoaded = true;
      });

    this._sessionService
      .getMatchsByIdSession$(idSession)
      .subscribe((matchs: MatchInput[]) => {
        if (matchs.length == 0) {
          this.isEmpty = true;
        }
        this.matchs = matchs;
      });
  }

  //naviguer vers la page current session grâce à l id
  public navigate(idSession: number): void {
    console.log(idSession);
    this._router.navigateByUrl('/edit-session/' + idSession);
  }
}
