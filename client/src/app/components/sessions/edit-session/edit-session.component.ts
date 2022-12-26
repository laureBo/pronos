import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { MatchInput } from 'src/app/common/model/match.input.model';
import { MatchService } from 'src/app/common/services/match.service';
import { SessionService } from 'src/app/common/services/session.service';
import { MatchComponent } from '../../match/match.component';
import { Match } from '../../match/match.models';

@Component({
  selector: 'app-edit-session',
  templateUrl: './edit-session.component.html',
  styleUrls: ['./edit-session.component.scss'],
})
export class EditSessionComponent implements OnInit {
  public matchs: MatchInput[];
  public idSession: number;

  constructor(
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
}
