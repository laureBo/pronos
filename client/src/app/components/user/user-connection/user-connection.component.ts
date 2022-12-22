import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-user-connection',
  templateUrl: './user-connection.component.html',
  styleUrls: ['./user-connection.component.scss'],
})
export class UserConnectionComponent implements OnInit {
  connectionUserFG: FormGroup;

  constructor(private formbuilder: FormBuilder) {}

  ngOnInit(): void {
    this.onInitFGconnection();
  }

  onInitFGconnection() {
    this.connectionUserFG = this.formbuilder.group({
      pseudoFC: '',
      passwordFC: '',
    });
  }
  onSubmitCallConnexion() {
    console.log(this.connectionUserFG);
  }
}
