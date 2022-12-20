import { Component, Input, OnInit } from '@angular/core';
import { Match } from '../../match/match.models';
import { SessionSummaryComplete } from '../session-summary/session-summary.model';
import { SessionsComponent } from '../sessions.component';

@Component({
  selector: 'app-current-session',
  templateUrl: './current-session.component.html',
  styleUrls: ['./current-session.component.scss'],
})
export class CurrentSessionComponent implements OnInit {
  @Input()
  inputSessionSummaryComplete: SessionSummaryComplete;

  public matchs: SessionSummaryComplete[];

  constructor(private session: SessionsComponent) {}

  ngOnInit(): void {}
}
