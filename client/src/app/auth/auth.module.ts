import { HasRoleDirective } from '../directives/has-role.directive';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AuthModuleRouting } from './auth.module.routing';
import { HttpClientModule } from '@angular/common/http';
import { AuthService } from './service/auth.service';
import { ReactiveFormsModule } from '@angular/forms';
import { UserSettingComponent } from './user-setting/user-setting.component';
import { IsAuthenticatedDirective } from '../directives/is-authenticated.directive';

@NgModule({
  imports: [
    CommonModule,
    AuthModuleRouting,
    HttpClientModule,
    BrowserAnimationsModule,
    ReactiveFormsModule
  ],
  providers: [
    AuthService
  ],
  declarations: [
    LoginComponent,
    RegisterComponent,
    UserSettingComponent,
    HasRoleDirective,
    IsAuthenticatedDirective],
  exports: [
    HasRoleDirective,
    IsAuthenticatedDirective
  ]
})
export class AuthModule { }
