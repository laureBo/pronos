import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-dialog-content-dialog',
  templateUrl: './dialog-content-dialog.component.html',
  styleUrls: ['./dialog-content-dialog.component.scss'],
})
export class DialogContentDialogComponent implements OnInit {
  constructor(public dialogRef: MatDialogRef<DialogContentDialogComponent>) {}
  public sessionName: string;
  ngOnInit(): void {}

  onNoClick(): void {
    this.dialogRef.close();
  }
}
