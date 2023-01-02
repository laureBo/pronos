import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatchInput } from 'src/app/common/model/match.input.model';
import { PariDetail } from 'src/app/common/model/pariDetail.models';
import { SessionInput } from 'src/app/common/model/session.input.model';
import { SessionService } from 'src/app/common/services/session.service';
import { SessionMapperService } from '../session-mapper.service';
import { SessionSummaryComplete } from '../session-summary/session-summary.model';
import { AuthenticationService } from '../../../common/services/authentication.service';
import { MessageService } from 'primeng/api';
import { MatDialog } from '@angular/material/dialog';
import { BetInput } from 'src/app/common/model/bet.input.model';
import { BetService } from 'src/app/common/services/bet.service';

@Component({
  selector: 'app-current-session',
  templateUrl: './current-session.component.html',
  providers: [MessageService],
  styleUrls: ['./current-session.component.scss'],
})
export class CurrentSessionComponent implements OnInit {
  inputSessionSummaryComplete!: SessionSummaryComplete;
  parisDetail: PariDetail[];
  clonedParisDetail: { [s: string]: PariDetail } = {};

  isLoaded = false;
  isEmpty = false;

  constructor(
    private _router: Router,
    private _route: ActivatedRoute,
    private _sessionService: SessionService,
    private _sessionMapper: SessionMapperService,
    private _betService: BetService,
    private _authentService: AuthenticationService,
    private messageService: MessageService,
    public dialog: MatDialog
  ) {}

  ngOnInit(): void {
    //on recupere l id selectionné dans l url
    const idSession: number = Number(
      this._route.snapshot.paramMap.get('id') as string
    );
    console.log(idSession);

    //on recupere le pseudo de l utilisateur
    const pseudo: string = this._authentService.getCurrentUser();

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
      .getParisDetailByPseudoAndSession$(idSession, pseudo)
      .subscribe((parisDetail: PariDetail[]) => {
        if (parisDetail.length == 0) {
          this.isEmpty = true;
        }
        this.parisDetail = parisDetail;
      });
  }

  //naviguer vers la page current session grâce à l id
  public navigate(idSession: number): void {
    console.log(idSession);
    this._router.navigateByUrl('/edit-session/' + idSession);
  }

  //initialisation du clone avant la modification d une ligne
  onRowEditInit(pariDetail: PariDetail) {
    this.clonedParisDetail[pariDetail.idMatch] = { ...pariDetail };
  }

  //creation de l objet, supression du clone si ok, et enregistrement en bd
  onRowEditSave(pariEquipe1: number, pariEquipe2: number, idMatch: number) {
    if (pariEquipe1 >= 0 && pariEquipe2 >= 0) {
      const bet: BetInput = {
        pseudo: this._authentService.getCurrentUser(),
        idMatch: idMatch,
        betEquipe1: pariEquipe1,
        betEquipe2: pariEquipe2,
      };

      delete this.clonedParisDetail[idMatch];
      this._betService.createNewBet$(bet).subscribe((message: String) => {
        this.messageService.add({
          severity: 'success',
          summary: 'Success',
          detail: 'Bet is updated',
        });
      });
    } else {
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'Invalid Bet',
      });
    }
  }

  onRowEditCancel(pariDetail: PariDetail, index: number) {
    this.parisDetail[index] = this.clonedParisDetail[pariDetail.idMatch];
    delete this.parisDetail[pariDetail.idMatch];
  }
}
