import { AuthService } from './../service/auth.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { RegisterTO } from '../model/register-to';
import { RegisterAs } from '../model/register-as';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  retisterAsEnum = RegisterAs;
  regAs: string[];

  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly authService: AuthService
  ) {
    this.regAs = Object.keys(this.retisterAsEnum).filter(f => !isNaN(Number(f)));
  }

  ngOnInit() {
    this.initForm();
  }

  private onRegister() {
    if (this.registerForm.valid) {

      const registerData: RegisterTO = {
        name: this.registerForm.controls['name'].value,
        surname: this.registerForm.controls['surname'].value,
        email: this.registerForm.controls['email'].value,
        password: this.registerForm.controls['password'].value,
        registrationType: this.registerForm.controls['registerAs'].value
      }

      this.authService.register(registerData).subscribe(
        (res) => {
          console.log(res);
          this.navigateToLoginPage();
        });
    }
  }

  private navigateToLoginPage() {

  }

  private initForm() {
    this.registerForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]],
      surname: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(60)]],
      email: ['', [Validators.required, Validators.email, Validators.maxLength(60)]],
      password: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(40)]],
      passwordConfirm: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(40)]],
      registerAs: [RegisterAs.AUTHOR, [Validators.required]]
    }, {
        validator: this.passwordMatchValidator
      });
  }

  private passwordMatchValidator(frm: FormGroup): any {
    return frm.controls['password'].value === frm.controls['passwordConfirm'].value ? null : { 'mismatch': true };
  }

}