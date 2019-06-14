import { ToastrService } from 'ngx-toastr';
import { AuthStorageService } from './../service/auth-storage.service';
import { Component, OnInit } from '@angular/core';
import { UserSettingTO } from '../model/user-setting';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AcademicTitle } from '../model/academic-title';

@Component({
  selector: 'app-user-setting',
  templateUrl: './user-setting.component.html',
  styleUrls: ['./user-setting.component.css']
})
export class UserSettingComponent implements OnInit {

  private readonly userEmail: string;
  private settingForm: FormGroup;
  private userData: UserSettingTO;

  academicTitleEnum = AcademicTitle;
  academicTitles: string[];

  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly route: ActivatedRoute,
    private readonly authService: AuthService,
    private readonly authStorageService: AuthStorageService,
    private readonly toastr: ToastrService,
    private readonly router: Router
  ) {
    this.userEmail = this.route.snapshot.paramMap.get('email')
    this.academicTitles = Object.keys(this.academicTitleEnum).filter(f => !isNaN(Number(f)));
  } ł

  ngOnInit() {
    if (this.userEmail) {
      this.authService.getUserSettingData(this.userEmail).subscribe(
        (data: UserSettingTO) => {
          this.userData = data;
          console.log(data);
          this.initForm();
        },
        (error: any) => {
          this.toastr.error(error.error.message, "Ups! Something went wrong");
          this.router.navigate(['conference-overview']);
        }
      )
    }
  }

  private initForm() {
    this.settingForm = this.formBuilder.group({
      name: [this.userData.name, [Validators.required, Validators.minLength(3), Validators.maxLength(50)]],
      surname: [this.userData.surname, [Validators.required, Validators.minLength(3), Validators.maxLength(60)]],
      aboutMe: [this.userData.aboutMe, []],
      url: [this.userData.url, []],
      academicTitle: [this.academicTitleEnum[this.userData.title], []]
    }, {
        academicTitleValidator: this.academicTitleRequired
      });
  }

  private onSave() {
    if (this.settingForm.valid) {
      this.userData.name = this.settingForm.controls['name'].value
      this.userData.surname = this.settingForm.controls['surname'].value
      this.userData.aboutMe = this.settingForm.controls['aboutMe'].value
      this.userData.url = this.settingForm.controls['url'].value
      this.userData.title = this.settingForm.controls['academicTitle'].value
      this.userData.email = this.userEmail;

      this.authService.saveUserDetails(this.userData).subscribe(
        (res) => {
          this.toastr.success("Your user details were sucessfuli updated", "Update sucessful", { timeOut: 1500 })
          this.router.navigate(['conference-overview']);
        },
        (error: any) => {

          this.toastr.error(error.error.message, "Ups! Something went wrong");
        }
      );
    }
  }

  private onLogOut() {
    this.authStorageService.signOut();
    this.toastr.success("You are sucessfully sign out", null, { timeOut: 1000 });
    this.router.navigate(["/login"]);
  }

  private academicTitleRequired(frm: FormGroup): any {
    let role = this.authStorageService.getUserAuthority();
    return (role.includes("AUTHOR") || role.includes("REVIEWER")) && frm.controls["academicTitle"] === null ? null : { 'required': true };
  }

}
