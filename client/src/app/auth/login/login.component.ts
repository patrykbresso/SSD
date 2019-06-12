import { AuthStorageService } from './../service/auth-storage.service';
import { AuthService } from './../service/auth.service';
import { JwtResponseTO } from './../model/jwt-response-to';
import { LoginTO } from './../model/login-to';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginAs } from '../model/login-as';
import { Router } from "@angular/router";
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  loginAsEnum = LoginAs;
  logAs: string[];

  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly authService: AuthService,
    private readonly authStorageService: AuthStorageService,
    private readonly router: Router,
    private readonly toastr: ToastrService) {
    this.logAs = Object.keys(this.loginAsEnum).filter(f => !isNaN(Number(f)));
  }

  ngOnInit() {
    this.initForm();
  }

  private onLogin() {
    if (this.loginForm.valid) {

      const loginData: LoginTO = {
        email: this.loginForm.controls['email'].value,
        password: this.loginForm.controls['password'].value,
        loginType: this.loginForm.controls['loginAs'].value
      }

      this.authService.login(loginData)
        .subscribe(
          (data: JwtResponseTO) => {
            this.toastr.success("You are successfully logged in.", "Login successful", { timeOut: 1500 });
            this.authStorageService.saveAuthData(data);
            this.navigateToConferenceOverview();
          },
          (error: any) => {
            this.toastr.error(error.error.message, "Ups! Something went wrong");
          });
    }
  }

  private initForm() {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(60)]],
      password: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(40)]],
      loginAs: [LoginAs.AUTHOR, [Validators.required]]
    });
  }

  private navigateToConferenceOverview() {
    this.router.navigate(['/conference-overview'])
  }
}
