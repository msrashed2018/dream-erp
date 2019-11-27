import { Component, OnInit } from '@angular/core';
import { COLORS, NO_DATA_FOUND, CLOTH_TYPES, EXPENSE_TYPES, EXPENSES } from '../../../../app-words';
import { ConfirmModalService } from '../../../confirm-modal/confirm-modal.service';
import { Router } from '@angular/router';
import { COLOR_PAGE_SIZE, COLOR_PAGE_SORT, PAGINATION_MAX_SIZE, EXPENSE_TYPES_PAGE_SIZE, EXPENSE_TYPES_PAGE_SORT, EXPENSES_PAGE_SIZE, EXPENSES_PAGE_SORT } from '../../../../app.constants';
import { Expense } from '../../../../model/expense.model';
import { ExpenseService } from '../../../../services/expense.service';
import { ErrorModalService } from '../../../error-modal/error-modal.service';
import { TouchSequence } from 'selenium-webdriver';
import { ExpenseType } from '../../../../model/expense-type.model';
import { ExpenseTypeService } from '../../../../services/administration/expense-type.service';
import { TokenStorageService } from '../../../../services/authentication/jwt/token-storage.service';

@Component({
  selector: 'app-list-expenses',
  templateUrl: './list-expenses.component.html',
  styleUrls: ['./list-expenses.component.scss']
})
export class ListExpensesComponent implements OnInit {
  title = EXPENSES;
  noDataFound = "";
  expenses: Expense[] = [];
  expenseTypes: ExpenseType[] = [];
  filteredExpenseTypeId: number = 0;
  filteredDateFrom: string = ""
  FiltereddateTo: string = ""
  totalPrice: number = 0;
  isAdmin :boolean = false

  //pagination variables
  totalItems: number = 0;
  currentPage: number = 0;
  numPages: number = 0;
  items: number = 0;
  itemsPerPage: number = EXPENSE_TYPES_PAGE_SIZE;

  constructor(
    private expenseService: ExpenseService,
    private expenseTypeService: ExpenseTypeService,
    private router: Router,
    private confirmationModalService: ConfirmModalService,
    private errorModalService: ErrorModalService,
    private tokenStorageService: TokenStorageService
  ) {
  }

  pageChanged(event: any): void {

    this.items = (event.page - 1) * this.itemsPerPage;
    this.refreshData(event.page - 1);
  }

  ngOnInit() {
    this.isAdmin = this.tokenStorageService.hasAdminRole();
    this.refreshData(this.currentPage);
    this.getExpenseTypes();
  }

  getExpensesTotalPrice(){
    if (this.filteredExpenseTypeId == null) {
      this.filteredExpenseTypeId = 0;
    }
    this.expenseService.retrieveExpensesTotalPrice(this.filteredExpenseTypeId, this.filteredDateFrom, this.FiltereddateTo).subscribe(
      response => {
        this.totalPrice = response as any;
      },
      error => {
        console.log('oops', error);
        if (error.error.message != null) {
          this.errorModalService.error("ERROR", error.error.message);
        } else {
          this.errorModalService.error("ERROR", "حدث خطا في النظام.. الرجاء المحاولة لاحقا");
        }
      }
    )
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
  refreshData(page) {

    if (this.filteredExpenseTypeId == null) {
      this.filteredExpenseTypeId = 0;
    }
    this.expenseService.retrieveExpenses(this.filteredExpenseTypeId, this.filteredDateFrom, this.FiltereddateTo, page, EXPENSES_PAGE_SIZE, EXPENSES_PAGE_SORT).subscribe(
      response => {
        this.expenses = response['content'];
        this.totalItems = response['totalElements'];
        if (response['content'].length != 0) {
          this.noDataFound = "";
        } else {
          this.noDataFound = NO_DATA_FOUND;
        }
        this.getExpensesTotalPrice();
      },
      error => {
        console.log('oops', error);
        this.noDataFound = "";
        if (error.error.message != null) {
          this.errorModalService.error("ERROR", error.error.message);
        } else {
          this.errorModalService.error("ERROR", "حدث خطا في النظام.. الرجاء المحاولة لاحقا");
        }
      }
    )
  }

  onDelete(id) {
    this.confirmationModalService.confirm('برجاء التاكيد', 'هل انت متاكد من حذف نوع النفقة ')
      .then((confirmed) => {
        if (confirmed) {
          this.expenseService.deleteExpense(id).subscribe(
            response => {
              this.refreshData(this.currentPage - 1);
            },
            error => {
              console.log('oops', error)
              if (error.error.message != null) {
                this.errorModalService.error("ERROR", error.error.message);
              } else {
                this.errorModalService.error("ERROR", "حدث خطا في النظام.. الرجاء المحاولة لاحقا");
              }
            }
          )
        }
      })
  }
  onEdit(id) {
    this.router.navigate(['administration/expenses', id, { componentMode: "editMode" }])
  }
  onAdd() {
    this.router.navigate(['administration/add-expense'])
  }

  onFilteredProductChanged(expenseTypeId){
    this.refreshData(0);
  }

  onFilteredDateFromChanged(dateFrom){
    this.refreshData(0);
  }
  onFilteredDateToChanged(dateFrom){
    this.refreshData(0);
  }

}
