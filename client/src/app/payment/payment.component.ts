import { Component, OnInit } from '@angular/core';
import {Location} from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {
  amount: number;

  constructor(private _location: Location, private router: Router) {
    this.amount = 10.00;
  }

  ngOnInit() {
  }

  cancel() {
    this._location.back();
  }

  payNow() {
    this.router.navigateByUrl('/api/payments/complete/payment?conferenceId=1');
  }

}
