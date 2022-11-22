import { Component, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormControl,
  FormGroup,
  NgForm,
  ValidationErrors,
  ValidatorFn,
  Validators,
} from '@angular/forms';
import { SessionOutput } from 'src/app/common/model/session.input.model';
import { AuthenticationService } from 'src/app/common/services/authentication.service';
import { SessionService } from 'src/app/common/services/session.service';
import { SessionSummary } from '../session-summary/session-summary.model';

@Component({
  selector: 'app-create-session',
  templateUrl: './create-session.component.html',
  styleUrls: ['./create-session.component.scss'],
})
export class CreateSessionComponent implements OnInit {
  sessionFG!: FormGroup;
  obligatoryLetter = 'e';
  sessions: SessionSummary[] = [];

  constructor(
    private formbuilder: FormBuilder,
    private _authent: AuthenticationService,
    private _sessionService: SessionService
  ) {}

  ngOnInit(): void {
    this.onInitFGsession();
  }

  onInitFGsession() {
    this.sessionFG = this.formbuilder.group({
      nameSessionFC: ['', [Validators.required, Validators.minLength(3)]],
    });
  }
  isAskedNewSession(): boolean {
    return true;
  }

  onSubmitCreateSession() {
    console.log(this.sessionFG.value);
    const newSession: SessionOutput = {
      nomSession: this.sessionFG.value,
      pseudoCreateur: this._authent.getCurrentUser(),
    };
    this._sessionService
      .createNewSession$(newSession)
      .subscribe((valueReturned: string) => {
        console.log('retour: ' + valueReturned);
      });
    this.sessionFG.reset();
  }

  obligatoryChar(): ValidatorFn {
    return (control: AbstractControl): null | ValidationErrors => {
      if (control.value.includes('e')) {
        return null;
      } else {
        return { obligatoryChar: 'pas de e' };
      }
    };
  }
}
