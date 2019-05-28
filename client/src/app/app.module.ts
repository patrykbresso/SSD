import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import {MatToolbarModule} from '@angular/material/toolbar';

import { AppComponent } from './app.component';
import { TabbarComponent } from './tabbar/tabbar.component';
import { AuthModule } from './auth/auth.module';
import { AppModuleRouting } from './app.module.routing';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { httpInterceptorProviders } from './interceptor/auth.interceptor';
import { AuthStorageService } from './auth/service/auth-storage.service';


@NgModule({
  declarations: [
    AppComponent,
    TabbarComponent,
    PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    AppModuleRouting,
    AuthModule
  ],
  providers: [
    httpInterceptorProviders,
    AuthStorageService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
