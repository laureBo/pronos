import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Match } from './match.models';
import { MatchService } from 'src/app/common/services/match.service';
import { formatDate } from '@angular/common';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-match',
  templateUrl: './match.component.html',
  styleUrls: ['./match.component.scss'],
})
export class MatchComponent implements OnInit {
  matchFG!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private _matchService: MatchService,
    public dialogRef: MatDialogRef<MatchComponent>,
    @Inject(MAT_DIALOG_DATA) public match: Match
  ) {}

  ngOnInit(): void {
    this.onInitFGmatch();
  }

  //instanciation formulaire match
  onInitFGmatch() {
    this.matchFG = this.formBuilder.group({
      equipe1Input: ['', [Validators.required, Validators.minLength(3)]],
      equipe2Input: ['', [Validators.required, Validators.minLength(3)]],
      dateMatch: ['', [Validators.required]],
    });
  }

  public generateMatch(): Match {
    return {
      equipe1: this.matchFG.controls['equipe1Input'].value,
      equipe2: this.matchFG.controls['equipe2Input'].value,
      dateMatch: this.matchFG.controls['dateMatch'].value,
    };
  }

  //bouton pour fermer la pop-up
  onNoClick(): void {
    this.dialogRef.close();
  }

  // fonction qui regarde si la date du match est passé ou non
  //isPassedDate(dateMatch: Date): boolean | any {
  //let today = new Date();
  //if (dateMatch.getTime() < today.getTime()) {
  //return true;
  // } else {
  // return false;
  // }
  //}
}
