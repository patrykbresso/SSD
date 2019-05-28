import { JwtResponseTO } from './../model/jwt-response-to';
import { Injectable } from '@angular/core';

@Injectable()
export class AuthStorageService {

  constructor() { }

  public saveAuthData(data: JwtResponseTO) {
    localStorage.setItem('ssd_token', data.token);
    localStorage.setItem('ssd_authority', data.authorities[0].name);
    localStorage.setItem('ssd_user_email', data.username);
  }

  public signOut() {
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
