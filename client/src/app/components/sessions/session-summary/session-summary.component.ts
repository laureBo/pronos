import { Component, Input, OnInit } from '@angular/core';
import { SessionSummary } from './session-summary.model';
import { SessionsComponent } from '../sessions.component';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/common/services/authentication.service';

@Component({
  selector: 'app-session-summary',
  templateUrl: './session-summary.component.html',
  styleUrls: ['./session-summary.component.scss'],
})
export class SessionSummaryComponent implements OnInit {
  @Input()
  inputSessionSummary: SessionSummary;

  constructor(
    private _authent: AuthenticationService,
    private _router: Router
  ) {}

  ngOnInit(): void {}

  navigate(url: string): void {
    console.log(this.inputSessionSummary.id);
    this._router.navigateByUrl(url);
  }

  //informe si l'utilisateur est aussi l administrateur
  public isCreator(): boolean {
    const creator = this.inputSessionSummary.creator;
    if (creator == this._authent.getCurrentUser()) {
      return true;
    }
    return false;
  }
}
