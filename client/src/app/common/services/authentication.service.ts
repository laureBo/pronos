import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  private _isAuthenticated = false;

  private _authData = '';

  private _currentUser = '';

  constructor() {}

  getCurrentUser(): string {
    return this._currentUser;
  }

  isVip(): boolean {
    return true;
  }

  isAuthenticated(): boolean {
    return this._isAuthenticated;
  }

  setAuthenticated(isAuthent: boolean): void {
    this._isAuthenticated = isAuthent;
    if (!isAuthent) {
      this._authData = '';
      this._currentUser = '';
    }
  }

  getAuthData(): string {
    return this._authData;
  }

  setAuthData(pseudo: string, password: string): void {
    const authData = window.btoa(`${pseudo}:${password}`);
    this._authData = authData;
    this.setCurrentUser(pseudo);
  }

  setCurrentUser(pseudo: string): void {
    this._currentUser = pseudo;
  }
}
