import {Routes, RouterModule} from '@angular/router';
import {ModuleWithProviders} from '@angular/core';
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';
import {ConferencesOverviewComponent} from "./conference/conferences-overview/conferences-overview.component";
import {ConferenceDetailsComponent} from "./conference/conference-details/conference-details.component";

const routes: Routes = [
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: '**', component: PageNotFoundComponent},
  {
    path: 'conference-overview',
    component: ConferencesOverviewComponent,
  },
  {
    path: 'conference/:id',
    component: ConferenceDetailsComponent,
  },
];

export const AppModuleRouting: ModuleWithProviders = RouterModule.forRoot(routes);
