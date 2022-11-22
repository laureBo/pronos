import { Component, Input, OnInit } from '@angular/core';
import { SessionSummary } from './session-summary.model';
import { SessionsComponent } from '../sessions.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-session-summary',
  templateUrl: './session-summary.component.html',
  styleUrls: ['./session-summary.component.scss'],
})
export class SessionSummaryComponent implements OnInit {
  @Input()
  inputSessionSummary: SessionSummary;

  constructor(private session: SessionsComponent, private _router: Router) {}

  ngOnInit(): void {}

  navigate(url: string): void {
    console.log(this.inputSessionSummary.id);
    this.session.onSelectedSession(this.inputSessionSummary.id);
    this._router.navigateByUrl(url);
  }
}
