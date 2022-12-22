import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import {
  SessionInput,
  SessionLightInput,
} from 'src/app/common/model/session.input.model';
import { SessionOutput } from 'src/app/common/model/session.output.model';
import { AuthenticationService } from 'src/app/common/services/authentication.service';
import { SessionService } from 'src/app/common/services/session.service';
import { DialogContentDialogComponent } from './dialog-content-dialog/dialog-content-dialog.component';
import { SessionMapperService } from './session-mapper.service';
import { SessionSummaryComponent } from './session-summary/session-summary.component';
import { SessionSummary } from './session-summary/session-summary.model';

@Component({
  selector: 'app-sessions',
  templateUrl: './sessions.component.html',
  styleUrls: ['./sessions.component.scss'],
})
export class SessionsComponent implements OnInit {
  public sessions: SessionSummary[];

  constructor(
    public dialog: MatDialog,
    private _router: Router,
    private _sessionService: SessionService,
    private _sessionMapper: SessionMapperService,
    private _authentService: AuthenticationService
  ) {}

  ngOnInit(): void {
    this._sessionService
      .getSessionsLightByUser$(this._authentService.getCurrentUser())
      .subscribe((sessionLightInputArray: SessionLightInput[]) => {
        this.sessions = sessionLightInputArray.map(
          (sessionLightInput: SessionLightInput) => {
            return this._sessionMapper.mapInputToComponent(sessionLightInput);
          }
        );
      });
  }

  //creer une session
  private _onSubmitCreateSession(sessionName: string) {
    console.log(sessionName);
    const newSession: SessionOutput = {
      pseudoCreateur: this._authentService.getCurrentUser(),
      nomSession: sessionName,
    };
    this._sessionService
      .createNewSession$(newSession)
      .subscribe((valueReturned: string) => {
        console.log('retour: ' + valueReturned);
      });
  }

  //ouvrir popup nom de session
  public openDialog(): void {
    const dialogRef = this.dialog.open(DialogContentDialogComponent);

    //valider et créer la session
    dialogRef.afterClosed().subscribe((sessionName: string) => {
      console.log('The dialog was closed');
      this._onSubmitCreateSession(sessionName);
    });
  }

  //naviguer vers la page current session grâce à l id
  navigate(idSession: number): void {
    console.log('idSession ' + idSession);
    this._router.navigateByUrl('sessions/' + idSession);
  }
}
