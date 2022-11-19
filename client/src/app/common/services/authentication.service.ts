import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  constructor() {}

  getCurrentUser(): string {
    return 'Mat';
  }
}
