import { Component, OnInit } from '@angular/core';
import { from, timeInterval, timestamp } from 'rxjs';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Match } from './match.models';
import { MatchService } from 'src/app/common/services/match.service';
import { formatDate } from '@angular/common';

@Component({
  selector: 'app-match',
  templateUrl: './match.component.html',
  styleUrls: ['./match.component.scss'],
})
export class MatchComponent implements OnInit {
  matchFG!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private _matchService: MatchService
  ) {}

  ngOnInit(): void {
    this.onInitFGmatch();
  }

  onInitFGmatch() {
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
    this._matchService
      .createNewBet$(match)
      .subscribe((valueReturned: string) => {
        console.log('retour: ' + valueReturned);
      });
    this.matchFG.reset();
  }

  isPassedDate(dateMatch: Date): boolean | any {
    let today = new Date();
    if (dateMatch.getTime() < today.getTime()) {
      return true;
    } else {
      return false;
    }
  }
}
