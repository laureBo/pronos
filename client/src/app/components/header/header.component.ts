import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/common/services/authentication.service';
import { NgClass } from '@angular/common';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {
  constructor(
    private _router: Router,
    public authentService: AuthenticationService
  ) {}

  ngOnInit(): void {}

  navigate(url: string): void {
    this._router.navigateByUrl(url);
  }

  public isAuthenticated(): boolean {
    return this.authentService.isAuthenticated();
  }
}
