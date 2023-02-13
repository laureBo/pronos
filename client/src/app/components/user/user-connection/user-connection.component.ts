import { AuthenticationService } from 'src/app/common/services/authentication.service';
import { UtilisateurService } from 'src/app/common/services/utilisateur.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { UserInput } from 'src/app/common/model/user.input.model';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-user-connection',
  templateUrl: './user-connection.component.html',
  styleUrls: ['./user-connection.component.scss'],
})
export class UserConnectionComponent implements OnInit {
  connectionUserFG: FormGroup;

  constructor(
    private formbuilder: FormBuilder,
    private _utilisateurService: UtilisateurService,
    private _router: Router,
    private _messageService: MessageService,
    private _authenticationService: AuthenticationService
  ) {}

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
    this._utilisateurService
      .connectUser$(
        this.connectionUserFG.get('pseudoFC')?.value,
        this.connectionUserFG.get('passwordFC')?.value
      )
      .subscribe({
        next: (user: UserInput) => {
          console.log('connection ok ' + user);
          this._router.navigateByUrl('home', { replaceUrl: true });
        },
        error: (e) => {
          this._messageService.add({
            severity: 'error',
            summary: 'Error',
            detail: 'Connexion impossible',
          });
          this._authenticationService.setAuthenticated(false);
        },
      });
  }
}
