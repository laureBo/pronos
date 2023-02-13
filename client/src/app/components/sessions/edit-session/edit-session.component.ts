import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { MajScore as Score } from 'src/app/common/model/majScore.input.model';
import { MatchInput } from 'src/app/common/model/match.input.model';
import { MatchService } from 'src/app/common/services/match.service';
import { SessionService } from 'src/app/common/services/session.service';
import { MatchComponent } from '../../match/match.component';
import { Match } from '../../match/match.models';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-edit-session',
  templateUrl: './edit-session.component.html',
  providers: [MessageService],
  styleUrls: ['./edit-session.component.scss'],
})
export class EditSessionComponent implements OnInit {
  public matchs: MatchInput[];
  public idSession: number;
  clonedMatchs: { [s: string]: MatchInput } = {};

  constructor(
    private messageService: MessageService,
    public dialog: MatDialog,
    private _matchService: MatchService,
    private _route: ActivatedRoute,
    private _sessionService: SessionService
  ) {}

  ngOnInit(): void {
    //on recupere l id selectionné dans l url
    this.idSession = Number(this._route.snapshot.paramMap.get('id') as string);
    console.log(this.idSession);

    this._loadMatchs();
  }

  //chargement de tous les matchs
  private _loadMatchs(): void {
    this._sessionService
      .getMatchsByIdSession$(this.idSession)
      .subscribe((matchs: MatchInput[]) => {
        this.matchs = matchs;
      });
  }

  //ouvrir une pop-up pour creer un match
  public openDialog(): void {
    const dialogRef = this.dialog.open(MatchComponent);

    //valider et créer la session
    dialogRef.afterClosed().subscribe((match: Match) => {
      console.log('The dialog was closed', match);

      if (match) {
        this._matchService
          .createNewMatch$(match, this.idSession)
          .subscribe((valueReturned: string) => {
            this._loadMatchs();
            console.log('retour: ' + valueReturned);
          });
      }
    });
  }

  //initialisation de la modification d une ligne
  onRowEditInit(match: MatchInput) {
    this.clonedMatchs[match.id] = { ...match };
  }

  //modification de la ligne
  onRowEditSave(scoreEquipe1: number, scoreEquipe2: number, idMatch: number) {
    if (scoreEquipe1 >= 0 && scoreEquipe2 >= 0) {
      const majScore: Score = {
        scoreEquipe1: scoreEquipe1,
        scoreEquipe2: scoreEquipe2,
      };

      delete this.clonedMatchs[idMatch];
      this._matchService
        .updateScoreMatch$(majScore, idMatch)
        .subscribe((message: String) => {
          this.messageService.add({
            severity: 'success',
            summary: 'Success',
            detail: 'Score is updated',
          });
        });
    } else {
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'Invalid Score',
      });
    }
  }

  //annulation de la modification
  onRowEditCancel(match: MatchInput, index: number) {
    this.matchs[index] = this.clonedMatchs[match.id];
    delete this.matchs[match.id];
  }

  //supprimer un match
  public deleteMatch(match: MatchInput) {
    let idSession = this.idSession;
    this._matchService.deleteMatch$(match).subscribe(() => this._loadMatchs());
  }
}
