import { AcademicTitle } from "./academic-title";

export interface UserSettingTO {

    email?: string,
    name: string,
    surname: string,
    aboutMe?: string,
    url?: string
    title?: AcademicTitle
}