import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormControl,
  NgForm,
  Validators,
  AbstractControl,
} from '@angular/forms';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss'],
})
export class UserComponent implements OnInit {
  inscriptionForm: FormGroup;

  constructor(private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.onInitInscriptionForm();
  }

  onInitInscriptionForm() {
    this.inscriptionForm = this.formBuilder.group({
      pseudoFC: ['', [Validators.required]],
      emailFC: ['', [Validators.required]],
      passwordFC: ['', [Validators.required]],
      nameFC: ['', [Validators.required, Validators.minLength(2)]],
      firstnameFC: ['', [Validators.required, Validators.minLength(2)]],
    });
  }

  onSubmitInscriptionForm() {
    console.log(this.inscriptionForm);
  }
}
