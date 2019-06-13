import { AuthStorageService } from './../auth/service/auth-storage.service';
import { HTTP_INTERCEPTORS, HttpEvent } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';

import { Observable } from 'rxjs';

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    constructor(private readonly authStorageService: AuthStorageService) { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if (this.authStorageService.getUserEmail() != null) {
            const headers = req.headers
                .set(TOKEN_HEADER_KEY, 'Bearer ' + this.authStorageService.getUserToken())
                .set('Content-Type', 'application/json');
            const reqClone = req.clone({
                headers
            });
            return next.handle(reqClone);
        }
        return next.handle(req);
    }
}

export const httpInterceptorProviders = [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
];