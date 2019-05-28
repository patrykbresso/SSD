import { LoginAs } from './login-as';

export interface LoginTO {
    email: string,
    password: string,
    loginType: LoginAs
}