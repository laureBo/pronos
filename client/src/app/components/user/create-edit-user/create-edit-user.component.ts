import { Component, OnDestroy, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormControl,
  NgForm,
  Validators,
  AbstractControl,
  ValidatorFn,
  ValidationErrors,
} from '@angular/forms';
import { disableDebugTools } from '@angular/platform-browser';
import { ActivatedRoute, withDisabledInitialNavigation } from '@angular/router';
import { Subscription } from 'rxjs';
import { Action } from 'rxjs/internal/scheduler/Action';
import { UserInput } from 'src/app/common/model/user.input.model';
import { UserOutput } from 'src/app/common/model/user.output.model';
import { ApiService } from 'src/app/common/services/api.service';

@Component({
  selector: 'app-create-edit-user',
  templateUrl: './create-edit-user.component.html',
  styleUrls: ['./create-edit-user.component.scss'],
})
export class CreateEditUserComponent implements OnInit, OnDestroy {
  public inscriptionFormGroup: FormGroup;
  public isOnCreationMode: boolean;
  private subscriptions: Subscription[] = [];

  constructor(
    private _route: ActivatedRoute,
    private _formBuilder: FormBuilder,
    private _apiService: ApiService
  ) {}
  ngOnDestroy(): void {
    this.subscriptions.forEach((subscripption: Subscription) =>
      subscripption.unsubscribe()
    );
    console.log('je destroy');
  }

  ngOnInit(): void {
    this.onInitInscriptionForm();
    const pseudo: string = this._route.snapshot.paramMap.get(
      'pseudo'
    ) as string;
    console.log(pseudo);
    this.isOnCreationMode = pseudo === null ? true : false;
    console.log(this.isOnCreationMode);
    if (!this.isOnCreationMode) {
      this.fillFormWithUserInfo(pseudo);
    }

    this.subscriptions.push(this.testValueChange() as Subscription);
  }

  testValueChange(): Subscription | undefined {
    return this.inscriptionFormGroup
      .get('pseudoFC')
      ?.valueChanges.subscribe((value: string) => {
        console.log(value);
      });
  }
  onInitInscriptionForm() {
    this.inscriptionFormGroup = this._formBuilder.group({
      pseudoFC: ['', [Validators.required]],
      emailFC: ['', [Validators.required]],
      passwordFC: ['', [this.passwordConditionRequired()]],
      nameFC: ['', [Validators.required, Validators.minLength(2)]],
      firstnameFC: ['', [Validators.required, Validators.minLength(2)]],
    });
  }

  passwordConditionRequired(): ValidatorFn {
    return (control: AbstractControl): null | ValidationErrors => {
      if (!this.isOnCreationMode) {
        return null;
      } else {
        return { passwordConditionRequired: 'entrez un mot de passe' };
      }
    };
  }

  onSubmitInscriptionForm() {
    console.log(this.inscriptionFormGroup);
    const newUser: UserOutput = {
      pseudo: this.inscriptionFormGroup.controls['pseudoFC'].value,
      mail: this.inscriptionFormGroup.controls['emailFC'].value,
      password: this.inscriptionFormGroup.controls['passwordFC'].value,
      nom: this.inscriptionFormGroup.controls['nameFC'].value,
      prenom: this.inscriptionFormGroup.controls['firstnameFC'].value,
    };
    this._apiService
      .createNewUser$(newUser)
      .subscribe((valueReturned: string) => {
        console.log('retour: ' + valueReturned);
      });
    this.inscriptionFormGroup.reset();
  }

  fillFormWithUserInfo(pseudo: string): void {
    this._apiService.getUser$(pseudo).subscribe((user: UserInput) => {
      this.inscriptionFormGroup.controls['pseudoFC'].setValue(user.pseudo);
      this.inscriptionFormGroup.controls['pseudoFC'].disable();
      this.inscriptionFormGroup.controls['emailFC'].setValue(user.mail);
      this.inscriptionFormGroup.controls['passwordFC'].setValue(user.password);
      this.inscriptionFormGroup.controls['passwordFC'].disable;
      this.inscriptionFormGroup.controls['nameFC'].setValue(user.nom);
      this.inscriptionFormGroup.controls['firstnameFC'].setValue(user.prenom);
    });
    //this.inscriptionForm.setValue();
  }
}
