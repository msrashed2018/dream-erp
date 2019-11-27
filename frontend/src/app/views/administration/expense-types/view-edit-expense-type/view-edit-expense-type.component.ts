import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ExpenseTypeService } from '../../../../services/administration/expense-type.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-view-edit-expense-type',
  templateUrl: './view-edit-expense-type.component.html',
  styleUrls: ['./view-edit-expense-type.component.scss']
})
export class ViewEditExpenseTypeComponent implements OnInit {
  expenseType = {};
  expenseTypeId;
  componentMode;
  disabled: boolean = false;
  successMessage: boolean = false;
  isCollapsed: boolean = false;
  iconCollapse: string = 'icon-arrow-up';
  errorMessage = "";
  constructor(private formBuilder: FormBuilder, private expenseTypeService: ExpenseTypeService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.forEach((urlParams) => {
      this.expenseTypeId = urlParams['id'];
      this.componentMode = urlParams['componentMode'];
      this.displayExpenseTypeDetails();

      if (this.componentMode == "editMode") {
        this.disabled = false;
      } else {
        this.disabled = true;
      }
    });
  }
  collapsed(event: any): void {
  }

  expanded(event: any): void {
  }
  toggleCollapse(): void {
    this.isCollapsed = !this.isCollapsed;
    this.iconCollapse = this.isCollapsed ? 'icon-arrow-down' : 'icon-arrow-up';
  }
  displayExpenseTypeDetails() {
    this.expenseTypeService.retrieveExpenseType(this.expenseTypeId).subscribe(
      response => {
        this.expenseType = response as any;
      }
    )
  }
  onSave() {

    this.expenseTypeService.updateExpenseType(this.expenseTypeId, this.expenseType).subscribe(
      result => {
        this.router.navigateByUrl("/administration/expenseTypes");
      },
      error => {
        if(error.status == 409){
          this.errorMessage = "تم تسجيل هذا اللون من قبل";
        }else{
          this.errorMessage = error.error.message;
        }
        
        console.log('oops', error);
        this.successMessage = false;
      }
    );
  }
  close() {
    this.router.navigateByUrl("/administration/expenseTypes");
  }
}
