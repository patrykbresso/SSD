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
    let role = this.getUserAuthority();
    if (role && role !== "") {
      this.isAuthenticatedSubject.next(true);
      this.currentRoleSubject.next(role);
    } else {
      this.isAuthenticatedSubject.next(false);
      this.currentRoleSubject.next(null);
    }
  }

  public saveAuthData(data: JwtResponseTO) {
    sessionStorage.setItem('ssd_token', data.token);
    sessionStorage.setItem('ssd_authority', data.authorities[0].name);
    sessionStorage.setItem('ssd_user_email', data.username);

    this.isAuthenticatedSubject.next(true);
    this.currentRoleSubject.next(data.authorities[0].name);
  }

  public signOut() {
    this.isAuthenticatedSubject.next(false);
    this.currentRoleSubject.next(null);
    sessionStorage.clear();
  }

  public getUserEmail(): string {
    return sessionStorage.getItem('ssd_user_email');
  }

  public getUserAuthority(): string {
    return sessionStorage.getItem('ssd_authority');
  }

  public getUserToken(): string {
    return sessionStorage.getItem('ssd_token');
  }
}
