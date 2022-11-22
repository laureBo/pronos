import { Component, OnInit, ViewChild } from '@angular/core';
import {
  SessionInput,
  SessionLightInput,
} from 'src/app/common/model/session.input.model';
import { AuthenticationService } from 'src/app/common/services/authentication.service';
import { SessionService } from 'src/app/common/services/session.service';
import { SessionMapperService } from './session-mapper.service';
import { SessionSummaryComponent } from './session-summary/session-summary.component';
import {
  SessionSummary,
  SessionSummaryComplete,
} from './session-summary/session-summary.model';

@Component({
  selector: 'app-sessions',
  templateUrl: './sessions.component.html',
  styleUrls: ['./sessions.component.scss'],
})
export class SessionsComponent implements OnInit {
  public sessions: SessionSummary[];

  public selectedSession: SessionSummary;

  constructor(
    private _sessionService: SessionService,
    private _sessionMapper: SessionMapperService,
    private _autentService: AuthenticationService
  ) {}

  ngOnInit(): void {
    this._sessionService
      .getSessionsLightByUser$(this._autentService.getCurrentUser())
      .subscribe((sessionLightInputArray: SessionLightInput[]) => {
        this.sessions = sessionLightInputArray.map(
          (sessionLightInput: SessionLightInput) => {
            return this._sessionMapper.mapInputToComponent(sessionLightInput);
          }
        );
      });
  }

  onSelectedSession(id: number): void {
    id = this.selectedSession.id;
    console.log(id);
    this._sessionService
      .getSessionById$(id)
      .subscribe((inputSessionComplete: SessionInput) => {
        return this._sessionMapper.mapInputCompleteToComponent(
          inputSessionComplete
        );
      });
  }
}
