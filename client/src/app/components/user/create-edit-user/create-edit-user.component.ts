import { Component, OnDestroy, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  AbstractControl,
  ValidatorFn,
  ValidationErrors,
} from '@angular/forms';
import {
  ActivatedRoute,
  Router,
  withDisabledInitialNavigation,
} from '@angular/router';
import { concat, concatMap, map, of, Subscription } from 'rxjs';
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
  public isOnCreationMode: boolean = true;
  private subscriptions: Subscription[] = [];

  constructor(
    private _route: ActivatedRoute,
    private _formBuilder: FormBuilder,
    private _apiService: ApiService,
    private _router: Router
  ) {}

  //pour eliminer automatiquement les subscribes deja utilises
  ngOnDestroy(): void {
    this.subscriptions.forEach((subscripption: Subscription) =>
      subscripption.unsubscribe()
    );
    console.log('je destroy');
  }

  ngOnInit(): void {
    this.onInitInscriptionForm();
    //on recupere le pseudo utilisateur dans l url
    const pseudo: string = this._route.snapshot.paramMap.get(
      'pseudo'
    ) as string;
    console.log(pseudo);
    //on verifie si on est en mode creation user
    //this.isOnCreationMode = pseudo === null ? true : false;
    //console.log(this.isOnCreationMode);
    //si 'non' on remplit le formulaire avec les données utilisateur
    if (pseudo != null) {
      this.isOnCreationMode = false;
      this.fillFormWithUserInfo(pseudo);
    }
  }

  //naviguer vers une autre page
  private navigate(url: string): void {
    this._router.navigateByUrl(url);
  }

  //instanciation du formulaire
  public onInitInscriptionForm() {
    this.inscriptionFormGroup = this._formBuilder.group({
      pseudoFC: ['', [Validators.required]],
      emailFC: ['', [Validators.required]],
      passwordFC: ['', [Validators.required, Validators.minLength(8)]],
      nameFC: ['', [Validators.required, Validators.minLength(2)]],
      firstnameFC: ['', [Validators.required, Validators.minLength(2)]],
    });
  }

  //condition du mdp ds le formulaire si 'mode creation utilisateur'
  //creation d un validator
  public passwordConditionRequired(): ValidatorFn {
    return (control: AbstractControl): null | ValidationErrors => {
      if (!this.isOnCreationMode) {
        return null;
      } else {
        return { passwordConditionRequired: 'entrez un mot de passe' };
      }
    };
  }

  //on attribut les valeurs et on reset le formulaire à la fin
  public onSubmitInscriptionForm() {
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
    this.navigate('/home');
  }

  //methode pour completer le formulaire avec les infos du user
  private fillFormWithUserInfo(pseudo: string): void {
    this._apiService.getUser$(pseudo).subscribe((user: UserInput) => {
      this.inscriptionFormGroup.controls['pseudoFC'].setValue(user.pseudo);
      this.inscriptionFormGroup.controls['pseudoFC'].disable();
      this.inscriptionFormGroup.controls['emailFC'].setValue(user.mail);
      this.inscriptionFormGroup.controls['passwordFC'].setValue(user.password);
      this.inscriptionFormGroup.controls['passwordFC'].disable();
      this.inscriptionFormGroup.controls['nameFC'].setValue(user.nom);
      this.inscriptionFormGroup.controls['firstnameFC'].setValue(user.prenom);
    });
    // this.inscriptionFormGroup.setValue();
  }
}

//this.bookList$ = this.bookForm.valueChanges
// .pipe(
//   debounceTime(100),
// switchMap(value => this._bookRepository.search({keywords: value.title}))
// );
//verifie si des données ont été modifiées
// private testValueChange(): Subscription {
//  const monObsorvable$ = this.inscriptionFormGroup?.valueChanges;
//this.inscriptionFormGroup.get('nameFC')?.valueChanges;
//   this.inscriptionFormGroup.get('firstnameFC')?.valueChanges;
//   return monObsorvable$!.subscribe((value: string) => {
// if(this.inscriptionFormGroup.valueChanges){
// this._apiService.updateUser$(monObsorvable$);
//     console.log(this.inscriptionFormGroup);
// });
// }
// les souscriptions ne s'unscribent pas toutes seules
//this.subscriptions.push(this.testValueChange() as Subscription);
