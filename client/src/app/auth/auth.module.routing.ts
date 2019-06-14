import { IsNotAutenticatedGuard } from './../guards/is-not-autenticated.guard';
import { IsAutenticatedGuard } from './../guards/is-autenticated.guard';
import { RouterModule, Routes } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { UserSettingComponent } from './user-setting/user-setting.component';

const routes: Routes = [
    {
        path: 'login',
        component: LoginComponent,
        canActivate: [IsNotAutenticatedGuard]
    },
    {
        path: 'register',
        component: RegisterComponent,
        canActivate: [IsNotAutenticatedGuard]
    },
    {
        path: 'user-detail/:email',
        component: UserSettingComponent,
        canActivate: [IsAutenticatedGuard]
    }
];

export const AuthModuleRouting: ModuleWithProviders = RouterModule.forChild(routes);