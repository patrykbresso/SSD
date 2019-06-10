import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { ToastrModule } from 'ngx-toastr';

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
import { ReviewComponent } from './review/review.component';
import { UploadPresentationComponent } from './register-exh-item/upload-presentation/upload-presentation.component';
import { UploadPosterComponent } from "./register-exh-item/upload-poster/upload-poster.component";
import { ConferencesOverviewResolver } from "./conference/conferences-overview/conferences-overview-resolver";
import { ConferenceService } from "./conference/conference-service";
import { ConferenceDetailsResolver } from "./conference/conference-details/conference-details-resolver";
import { IsAuthenticatedDirective } from './directives/is-authenticated.directive';
import { HasRoleDirective } from './directives/has-role.directive';


@NgModule({
  declarations: [
    AppComponent,
    TabbarComponent,
    PageNotFoundComponent,
    ConferenceDetailsComponent,
    ConferencesOverviewComponent,
    PaymentComponent,
    ReviewComponent,
    UploadPosterComponent,
    UploadPresentationComponent,
    IsAuthenticatedDirective,
    HasRoleDirective
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppModuleRouting,
    AuthModule,
    ToastrModule.forRoot({
      preventDuplicates: true
    })
  ],
  providers: [
    httpInterceptorProviders,
    AuthStorageService,
    ConferencesOverviewResolver,
    ConferenceService,
    ConferenceDetailsResolver
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
