import { AuthStorageService } from './../auth/service/auth-storage.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-tabbar',
  templateUrl: './tabbar.component.html',
  styleUrls: ['./tabbar.component.css']
})
export class TabbarComponent {

  constructor(
    private readonly authStorageService: AuthStorageService,
    private readonly router: Router,
    private readonly toastr: ToastrService,
  ) { }

  private onSetting() {
    let email = this.authStorageService.getUserEmail();
    if (email && email !== "") {
      this.router.navigate(['/user-detail/' + email])
    }
  }

  private onLogOut() {
    this.authStorageService.signOut();
    this.toastr.success("You are sucessfully sign out", null, { timeOut: 1000 });
    this.router.navigate(["/login"]);
  }

}
