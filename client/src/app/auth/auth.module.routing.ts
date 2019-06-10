import { RouterModule, Routes } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import {PaymentComponent} from "../payment/payment.component";

const routes: Routes = [
    {
        path: 'login',
        component: LoginComponent,
    },
    {
        path: 'register',
        component: RegisterComponent,
    },
    {
        path: 'payment',
        component: PaymentComponent,
    }
];

export const AuthModuleRouting: ModuleWithProviders = RouterModule.forChild(routes);
