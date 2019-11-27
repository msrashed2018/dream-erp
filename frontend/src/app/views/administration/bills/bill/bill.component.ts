import { Component, OnInit } from '@angular/core';
import { Bill } from '../../../../model/bill.model';

@Component({
  selector: 'app-bill',
  templateUrl: './bill.component.html',
  styleUrls: ['./bill.component.scss']
})
export class BillComponent implements OnInit {
  billImage ="bill-logo.png";

  bill: Bill;
  constructor() { }

  ngOnInit() {
    this.bill = new Bill();
    this.bill.id = 1;
    this.bill.to = "مدرسة الشيخ زايد";
    this.bill.notes = "لا توجد ملاحظات";
    this.bill.registeredDate = "14 Nov 2019";
  }

}
