import { Component, OnInit } from '@angular/core';
import { COLORS, NO_DATA_FOUND, CLOTH_TYPES, EXPENSE_TYPES } from '../../../../app-words';
import { ConfirmModalService } from '../../../confirm-modal/confirm-modal.service';
import { Router } from '@angular/router';
import { COLOR_PAGE_SIZE, COLOR_PAGE_SORT, PAGINATION_MAX_SIZE, EXPENSE_TYPES_PAGE_SIZE, EXPENSE_TYPES_PAGE_SORT } from '../../../../app.constants';
import { ExpenseType } from '../../../../model/expense-type.model';
import { ExpenseTypeService } from '../../../../services/administration/expense-type.service';
import { ErrorModalService } from '../../../error-modal/error-modal.service';

@Component({
  selector: 'app-list-expense-type',
  templateUrl: './list-expense-type.component.html',
  styleUrls: ['./list-expense-type.component.scss']
})
export class ListExpenseTypeComponent implements OnInit {
  title = EXPENSE_TYPES;
  noDataFound = "";
  expenseTypes: ExpenseType[];

  //pagination variables
  totalItems: number = 0;
  currentPage: number = 0;
  numPages: number = 0;
  items: number = 0;
  itemsPerPage: number = EXPENSE_TYPES_PAGE_SIZE;

  constructor(
    private expenseTypeService: ExpenseTypeService,
    private router: Router,
    private confirmationModalService: ConfirmModalService,
    private errorModalService: ErrorModalService
  ) {
  }

  pageChanged(event: any): void {

    this.items = (event.page - 1) * this.itemsPerPage;
    this.refreshData(event.page - 1);
  }

  ngOnInit() {
    this.refreshData(this.currentPage);
  }
  refreshData(page) {
    this.expenseTypeService.retrieveExpenseTypes(page, EXPENSE_TYPES_PAGE_SIZE, EXPENSE_TYPES_PAGE_SORT).subscribe(
      response => {
        this.expenseTypes = response['content'];
        this.totalItems = response['totalElements'];
        if (response['content'].length != 0) {
          this.noDataFound = "";
        } else {
          this.noDataFound = NO_DATA_FOUND;
        }
      },
      error => {
        console.log('oops', error);
        this.noDataFound = "";
        if (error.error.message != null) {
          this.errorModalService.error("ERROR", error.error.message);
        }else{
          this.errorModalService.error("ERROR", "حدث خطا في النظام.. الرجاء المحاولة لاحقا");
        }
      }
    )
  }

  onDelete(id) {
    this.confirmationModalService.confirm('برجاء التاكيد', 'هل انت متاكد من حذف نوع النفقة ')
      .then((confirmed) => {
        if (confirmed) {
          this.expenseTypeService.deleteExpenseType(id).subscribe(
            response => {
              this.refreshData(this.currentPage - 1);
            },
            error => {
              console.log('oops', error)
              if (error.error.message != null) {
                this.errorModalService.error("ERROR", error.error.message);
              }else{
                this.errorModalService.error("ERROR", "حدث خطا في النظام.. الرجاء المحاولة لاحقا");
              }
            }
          )
        }
      })
  }
  onEdit(id) {
    this.router.navigate(['administration/expenseTypes', id, { componentMode: "editMode" }])
  }
  onAdd() {
    this.router.navigate(['administration/add-expenseType'])
  }

}
