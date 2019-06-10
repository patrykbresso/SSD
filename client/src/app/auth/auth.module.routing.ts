import { RouterModule, Routes } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { UserSettingComponent } from './user-setting/user-setting.component';

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
        path: 'user-detail/:email',
        component: UserSettingComponent,
    }
];

export const AuthModuleRouting: ModuleWithProviders = RouterModule.forChild(routes);