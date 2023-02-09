import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
} from '@angular/router';
import { AuthenticationService } from './common/services/authentication.service';

@Injectable({
  providedIn: 'root',
})
/**
 * Service to check if the current user is allowed to access (admin or super admin roles requested)
 */
export class AuthGuardAdminService implements CanActivate {
  constructor(
    private _router: Router,
    private _authenticationService: AuthenticationService
  ) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean {
    const isAuthenticated = this._authenticationService.isAuthenticated();

    if (!isAuthenticated) {
      this._navigateTo(this._router, '/home');
    }

    return isAuthenticated;
  }

  /**
   * Route to the url in parameter
   *
   * @param model model
   */
  private _navigateTo(router: Router, url: string): void {
    router.navigateByUrl(url, { replaceUrl: true });
  }
}
