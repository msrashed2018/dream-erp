import { Component, OnInit } from '@angular/core';
import { ExpenseTypeService } from '../../../../services/administration/expense-type.service';
import { Router } from '@angular/router';
import { ErrorModalService } from '../../../error-modal/error-modal.service';
import { ExpenseService } from '../../../../services/expense.service';
import { ExpenseType } from '../../../../model/expense-type.model';
import { Expense } from '../../../../model/expense.model';
@Component({
  selector: 'app-add-expense',
  templateUrl: './add-expense.component.html',
  styleUrls: ['./add-expense.component.scss']
})
export class AddExpenseComponent implements OnInit {
  expense :Expense = new Expense();
  isCollapsed: boolean = false;
  iconCollapse: string = 'icon-arrow-up';
  expenseTypes: ExpenseType[] = [];
  selectedExpenseTypeId : number;
  constructor(
    private expenseService: ExpenseService,
    private expenseTypeService: ExpenseTypeService, 
    private router: Router,
    private errorModalService: ErrorModalService
    ) { }


  ngOnInit() {
    this.getExpenseTypes()
  }
  collapsed(event: any): void {
  }

  expanded(event: any): void {
  }
  toggleCollapse(): void {
    this.isCollapsed = !this.isCollapsed;
    this.iconCollapse = this.isCollapsed ? 'icon-arrow-down' : 'icon-arrow-up';
  }
  getExpenseTypes() {
    this.expenseTypeService.retrieveAllExpenseTypes().subscribe(
      response => {
        this.expenseTypes = response;
      },
      error => {
        console.log('oops', error);
        if (error.error.message != null) {
          this.errorModalService.error("ERROR", error.error.message);
        }else{
          this.errorModalService.error("ERROR", "حدث خطا في النظام.. الرجاء المحاولة لاحقا");
        }
      }
    )
  }
  onSave() {
    this.expense.expenseType = this.expenseTypes.find((c) => c.id == this.selectedExpenseTypeId);
    this.expenseService.createExpense(this.expense).subscribe(
      result => {
        this.router.navigateByUrl("/administration/expenses");
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
    this.router.navigateByUrl("/administration/expenses");
  }
}
