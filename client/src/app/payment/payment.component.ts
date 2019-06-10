import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {
  amount: number;

  constructor() {
    this.amount = 10.00;
  }

  ngOnInit() {
  }

  cancel() {

  }

  payNow() {

  }

}
