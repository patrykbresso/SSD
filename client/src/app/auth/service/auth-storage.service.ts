import { JwtResponseTO } from './../model/jwt-response-to';
import { Injectable } from '@angular/core';
import { ReplaySubject, BehaviorSubject } from 'rxjs';
import { distinctUntilChanged } from 'rxjs/operators';

@Injectable()
export class AuthStorageService {

  private isAuthenticatedSubject = new ReplaySubject<boolean>(1);
  private currentRoleSubject = new BehaviorSubject<string>({} as string);

  public isAuthenticated = this.isAuthenticatedSubject.asObservable();
  public currentRole = this.currentRoleSubject.asObservable().pipe(distinctUntilChanged());

  constructor() {
    this.isAuthenticatedSubject.next(false);
    this.currentRoleSubject.next(null);
  }

  public saveAuthData(data: JwtResponseTO) {
    this.isAuthenticatedSubject.next(true);
    this.currentRoleSubject.next(data.authorities[0].name);

    localStorage.setItem('ssd_token', data.token);
    localStorage.setItem('ssd_authority', data.authorities[0].name);
    localStorage.setItem('ssd_user_email', data.username);
  }

  public signOut() {
    this.isAuthenticatedSubject.next(false);
    this.currentRoleSubject.next(null);
    localStorage.clear();
  }

  public getUserEmail(): string {
    return localStorage.getItem('ssd_user_email');
  }

  public getUserAuthority(): string {
    return localStorage.getItem('ssd_authority');
  }

  public getUserToken(): string {
    return localStorage.getItem('ssd_token');
  }
}
