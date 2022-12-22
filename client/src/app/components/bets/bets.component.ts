import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TitleStrategy } from '@angular/router';
import { BetInput } from 'src/app/common/model/bet.input.model';
import { AuthenticationService } from 'src/app/common/services/authentication.service';
import { BetService } from 'src/app/common/services/bet.service';

@Component({
  selector: 'app-bets',
  templateUrl: './bets.component.html',
  styleUrls: ['./bets.component.scss'],
})
export class BetsComponent implements OnInit {
  pariFG!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private _betService: BetService,
    private _authentication: AuthenticationService
  ) {}

  ngOnInit(): void {
    this.onInitPariFG();
  }

  onInitPariFG(): void {
    this.pariFG = this.formBuilder.group({
      idMatchFG: ['', [Validators.required]],
      pari1FG: ['', [Validators.required]],
      pari2FG: ['', [Validators.required]],
    });
  }

  onSubmitCreateBet(): void {
    const betInput: BetInput = {
      pseudo: this._authentication.getCurrentUser(),
      idMatch: this.pariFG.controls['idMatchFG'].value,
      betEquipe1: this.pariFG.controls['pari1FG'].value,
      betEquipe2: this.pariFG.controls['pari1FG'].value,
    };
    this._betService
      .createNewBet$(betInput)
      .subscribe((valueReturned: string) => {
        console.log('retour: ' + valueReturned);
      });
    this.pariFG.reset();
  }
}
