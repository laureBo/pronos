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
import { StatsOutput } from 'src/app/common/model/stats.output.model';
import { concatMap, map, Observable } from 'rxjs';

@Component({
  selector: 'app-current-session',
  templateUrl: './current-session.component.html',
  providers: [MessageService],
  styleUrls: ['./current-session.component.scss'],
})
export class CurrentSessionComponent implements OnInit {
  inputSessionSummaryComplete!: SessionSummaryComplete;
  parisDetailFirst: PariDetail[];
  clonedParisDetail: { [s: string]: PariDetail } = {};
  isLoaded = false;
  isListMatchEmpty = false;
  participants: string[];
  stats: StatsOutput[];
  // classement: { [key: string]: number };
  columnsToDisplay = [
    'classement',
    'pseudo',
    'scoreBySession',
    'nbMatchsTrouves',
    'nbMatchsExacts',
    'nbMatchsSession',
  ];

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

    //on appelle la fct qui renvoit l objet Session complet (avec ses listes)
    this._sessionService
      .getSessionById$(idSession)
      .pipe(
        concatMap((inputSessionComplete: SessionInput) => {
          this.inputSessionSummaryComplete =
            this._sessionMapper.mapInputCompleteToComponent(
              inputSessionComplete
            );

          this.parisDetailFirst = inputSessionComplete.matchs.map(
            (match: MatchInput) => {
              const pariDetail: PariDetail = {
                idMatch: match.id,
                equipe1: match.equipe1,
                pariEquipe1: null,
                equipe2: match.equipe2,
                pariEquipe2: null,
                scoreEquipe1: match.scoreEquipe1,
                scoreEquipe2: match.scoreEquipe2,
                dateMatch: match.dateMatch,
                isWinner: null,
                isPerfect: null,
              };
              return pariDetail;
            }
          );
          if (this.parisDetailFirst.length == 0) {
            this.isListMatchEmpty = true;
          }
          //on recupere la liste de paris detail
          return this._sessionService.getParisDetailByPseudoAndSession$(
            idSession,
            pseudo
          );
        }),
        concatMap((parisDetailsLoaded: PariDetail[]) => {
          /*parisDetail.forEach((pariDetail: PariDetail) => {
            const toto: PariDetail = this.parisDetail.find(
              (localPariDetail: PariDetail) =>
                (localPariDetail.idMatch = pariDetail.idMatch)
            ) as unknown as PariDetail;
            const index = this.parisDetail.indexOf(toto);
            this.parisDetail[index] = pariDetail;
          });*/
          this.parisDetailFirst.map((pariDetailFirst: PariDetail) => {
            this.parisDetailLoaded.find(
              (pariDetailFinal: PariDetail) =>
                (pariDetailFirst.idMatch = pariDetailFinal.idMatch)
            );
            if (pariDetailFinal) {
              return pariDetailFinal;
            } else {
              return pariUser;
            }
          });

          //on appelle la fonction de classement
          return this._loadStatsSession$(idSession);
        }),
        map(() => this._loadParticipantsSession$(idSession))
      )
      .subscribe(() => {
        //parametre permettant d afficher que lorsque tout est chargé grace aux subscribe et ngif
        this.isLoaded = true;
      });
  }

  //naviguer vers la page edit session grâce à l id
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
        pariEquipe1: pariEquipe1,
        pariEquipe2: pariEquipe2,
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

  //verifie si l utilisateur est aussi admin de la session
  public isAdmin(pseudo: string): Boolean {
    if (pseudo == this._authentService.getCurrentUser()) {
      return true;
    }
    return false;
  }

  //afficher la liste des utilisateurs de la session
  private _loadParticipantsSession$(idSession: number): Observable<void> {
    return this._sessionService.getAllusersBySession$(idSession).pipe(
      map((participants: string[]) => {
        this.participants = participants;
      })
    );
  }

  //afficher le classement d'une session
  private _loadStatsSession$(idSession: number): Observable<void> {
    return this._sessionService.getAllStatsAndRankingBySession$(idSession).pipe(
      map((stats: StatsOutput[]) => {
        this.stats = stats;
      })
    );
  }
}
