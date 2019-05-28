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
import { ConferenceDetailsComponent } from './conference/conference-details/conference-details.component';
import { ConferencesOverviewComponent } from './conference/conferences-overview/conferences-overview.component';
import { PaymentComponent } from './payment/payment.component';
import { RegisterExhItemComponent } from './register-exh-item/register-exh-item.component';
import { ReviewComponent } from './review/review.component';
import { UploadComponent } from './upload/upload.component';
import { UploadPosterComponent } from './upload-poster/upload-poster.component';
import { UploadPresentationComponent } from './register-exh-item/upload-presentation/upload-presentation.component';


@NgModule({
  declarations: [
    AppComponent,
    TabbarComponent,
    PageNotFoundComponent,
    ConferenceDetailsComponent,
    ConferencesOverviewComponent,
    PaymentComponent,
    RegisterExhItemComponent,
    ReviewComponent,
    UploadComponent,
    UploadPosterComponent,
    UploadPresentationComponent
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
