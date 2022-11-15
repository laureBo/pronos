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

@Component({
  selector: 'app-create-session',
  templateUrl: './create-session.component.html',
  styleUrls: ['./create-session.component.scss'],
})
export class CreateSessionComponent implements OnInit {
  sessionFG!: FormGroup;
  obligatoryLetter = 'e';

  constructor(private formbuilder: FormBuilder) {}

  ngOnInit(): void {
    this.onInitFGsession();
  }

  onInitFGsession() {
    this.sessionFG = this.formbuilder.group({
      nameSessionFC: [
        '',
        [Validators.required, Validators.minLength(3), this.obligatoryChar()],
      ],
    });
  }

  onSubmitCreateSession() {
    console.log(this.sessionFG);
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
