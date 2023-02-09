import { NonNullAssert } from '@angular/compiler';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  constructor() {}

  getCurrentUser(): string {
    return 'Mat';
  }

  isVip(): boolean {
    return true;
  }

  isAuthenticated(): boolean {
    return true;
  }
}
