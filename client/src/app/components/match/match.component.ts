import { Component, OnInit } from '@angular/core';
import { from } from 'rxjs';
import {
  AbstractControl,
  FormBuilder,
  FormControl,
  FormGroup,
  NgForm,
  Validators,
} from '@angular/forms';
import { Match } from './match.models';

@Component({
  selector: 'app-match',
  templateUrl: './match.component.html',
  styleUrls: ['./match.component.scss'],
})
export class MatchComponent implements OnInit {
  matchFG!: FormGroup;

  constructor(private formBuilder: FormBuilder) {}

  ngOnInit(): void {}

  onInitFGsession() {
    this.matchFG = this.formBuilder.group({
      equipe1Input: ['', [Validators.required, Validators.minLength(3)]],
      equipe2Input: ['', [Validators.required, Validators.minLength(3)]],
      dateMatch: ['', [Validators.required]],
      score1Input: [''],
      score2Input: [''],
    });
  }

  onSubmitCreateMatch(): void {
    const match: Match = {
      equipe1: this.matchFG.controls['equipe1Input'].value,
      equipe2: this.matchFG.controls['equipe2Input'].value,
      dateMatch: this.matchFG.controls['dateMatch'].value,
      scoreEquipe1: this.matchFG.controls['score1Input'].value,
      scoreEquipe2: this.matchFG.controls['score2Input'].value,
    };
  }
}
