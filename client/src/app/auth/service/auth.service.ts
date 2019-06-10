import { RegisterTO } from './../model/register-to';
import { JwtResponseTO } from './../model/jwt-response-to';
import { LoginTO } from './../model/login-to';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserSettingTO } from '../model/user-setting';

const urlAuth: string = 'http://localhost:8080/api/auth/'
const urlUser: string = 'http://localhost:8080/api/user/'

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable()
export class AuthService {

  constructor(private readonly http: HttpClient) { }

  public login(loginData: LoginTO): Observable<JwtResponseTO> {
    return this.http.post<JwtResponseTO>(urlAuth + 'login', loginData, httpOptions);
  }

  public register(data: RegisterTO): Observable<string> {
    return this.http.post<string>(urlAuth + 'register', data, httpOptions);
  }

  public getUserSettingData(email: string): Observable<UserSettingTO> {
    return this.http.get<UserSettingTO>(urlUser + 'detail/' + email, httpOptions);
  }

  public saveUserDetails(data: UserSettingTO): Observable<string> {
    return this.http.patch<string>(urlUser + 'update', data, httpOptions);
  }
}
