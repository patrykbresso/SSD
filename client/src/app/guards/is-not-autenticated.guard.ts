import { Injectable } from '@angular/core';
import { AuthStorageService } from './../auth/service/auth-storage.service';
import { CanActivate, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class IsNotAutenticatedGuard implements CanActivate {

  constructor(
    private readonly authStorageService: AuthStorageService,
    private readonly router: Router,
    private readonly toastr: ToastrService
  ) { }

  canActivate(): boolean  {
    let email = this.authStorageService.getUserEmail();
    if(email === null || email === ""){
      return true;
    }
    this.router.navigate(["/conference-overview"]);
    return false;
  } 
  
}
