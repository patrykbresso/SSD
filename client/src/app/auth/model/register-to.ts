import { RegisterAs } from "./register-as";

export interface RegisterTO {
    name: string,
    surname: string,
    email: string,
    password: string,
    registrationType: RegisterAs
}