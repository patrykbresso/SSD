import { AuthStorageService } from './../service/auth-storage.service';
import { AuthService } from './../service/auth.service';
import { JwtResponseTO } from './../model/jwt-response-to';
import { LoginTO } from './../model/login-to';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly authService: AuthService,
    private readonly authStorageService: AuthStorageService) { }

  ngOnInit() {
    this.initForm();
  }

  private onLogin() {
    if (this.loginForm.valid) {

      const loginData: LoginTO = {
        email: this.loginForm.controls['email'].value,
        password: this.loginForm.controls['password'].value
      }

      this.authService.login(loginData)
        .subscribe(
          (data: JwtResponseTO) => {
            console.log(data);
            this.authStorageService.saveAuthData(data);
          });
    }
  }

  private initForm() {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(60)]],
      password: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(40)]]
    });
  }
}
