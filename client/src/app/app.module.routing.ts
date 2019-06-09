import {Routes, RouterModule} from '@angular/router';
import {ModuleWithProviders} from '@angular/core';
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';
import {ConferencesOverviewComponent} from "./conference/conferences-overview/conferences-overview.component";
import {ConferenceDetailsComponent} from "./conference/conference-details/conference-details.component";
import {ConferencesOverviewResolver} from "./conference/conferences-overview/conferences-overview-resolver";
import {ConferenceDetailsResolver} from "./conference/conference-details/conference-details-resolver";

const routes: Routes = [
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {
    path: 'conference-overview',
    component: ConferencesOverviewComponent,
    resolve: { overview: ConferencesOverviewResolver }
  },
  {
    path: 'conference/:id',
    component: ConferenceDetailsComponent,
    resolve: { details: ConferenceDetailsResolver }
  },
  {path: '**', component: PageNotFoundComponent},
];

export const AppModuleRouting: ModuleWithProviders = RouterModule.forRoot(routes);
