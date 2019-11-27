import { Component, OnInit } from '@angular/core';
import { ExpenseTypeService } from '../../../../services/administration/expense-type.service';
import { Router } from '@angular/router';
import { ErrorModalService } from '../../../error-modal/error-modal.service';
@Component({
  selector: 'app-add-expense-type',
  templateUrl: './add-expense-type.component.html',
  styleUrls: ['./add-expense-type.component.scss']
})
export class AddExpenseTypeComponent implements OnInit {
  expenseType = {};
  isCollapsed: boolean = false;
  iconCollapse: string = 'icon-arrow-up';
  constructor(
    private expenseTypeService: ExpenseTypeService, private router: Router,
    
    private errorModalService: ErrorModalService
    ) { }


  ngOnInit() {
  }
  collapsed(event: any): void {
  }

  expanded(event: any): void {
  }
  toggleCollapse(): void {
    this.isCollapsed = !this.isCollapsed;
    this.iconCollapse = this.isCollapsed ? 'icon-arrow-down' : 'icon-arrow-up';
  }
  onSave() {
    this.expenseTypeService.createExpenseType(this.expenseType).subscribe(
      result => {
        this.router.navigateByUrl("/administration/expenseTypes");
      },
      error => {
        console.log('oops', error);
        if (error.error.message != null) {
          this.errorModalService.error("ERROR", error.error.message);
        }else{
          this.errorModalService.error("ERROR", "حدث خطا في النظام.. الرجاء المحاولة لاحقا");
        }
      }
    );
  }
  close() {
    this.router.navigateByUrl("/administration/expenseTypes");
  }
}