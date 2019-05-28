import { RegisterTO } from './../model/register-to';
import { JwtResponseTO } from './../model/jwt-response-to';
import { LoginTO } from './../model/login-to';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const url: string = 'http://localhost:8080/api/auth/'

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable()
export class AuthService {

  constructor(private readonly http: HttpClient) { }

  public login(loginData: LoginTO): Observable<JwtResponseTO> {
    return this.http.post<JwtResponseTO>(url + 'login', loginData, httpOptions);
  }

  public register(data: RegisterTO): Observable<string> {
    return this.http.post<string>(url + 'register', data, httpOptions);
  }
}
