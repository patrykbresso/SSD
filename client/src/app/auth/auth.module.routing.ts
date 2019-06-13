import { RouterModule, Routes } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import {PaymentComponent} from "../payment/payment.component";
import {PaymentSuccessfulComponent} from "../payment/payment-successful/payment-successful.component";

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
    },
    {
        path: 'complete/payment',
        component: PaymentSuccessfulComponent,
    },
];

export const AuthModuleRouting: ModuleWithProviders = RouterModule.forChild(routes);
