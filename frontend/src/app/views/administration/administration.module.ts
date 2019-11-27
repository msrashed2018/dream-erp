import { NgModule } from '@angular/core';
import { AdministrationRoutingModule } from './administration-routing.module';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule, NgbModalModule } from '@ng-bootstrap/ng-bootstrap';
import { AlertModule } from 'ngx-bootstrap/alert';
import { CollapseModule } from 'ngx-bootstrap/collapse';
import { ConfirmModalService } from '../confirm-modal/confirm-modal.service';
import { PaginationModule } from 'ngx-bootstrap/pagination';
import { AuditListComponent } from './audit-list/audit-list.component';
import { ListColorComponent } from './colors/list-color/list-color.component';
import { AddColorComponent } from './colors/add-color/add-color.component';
import { ViewEditColorComponent } from './colors/view-edit-color/view-edit-color.component';
import { ListExpenseTypeComponent } from './expense-types/list-expense-type/list-expense-type.component';
import { AddExpenseTypeComponent } from './expense-types/add-expense-type/add-expense-type.component';
import { ViewEditExpenseTypeComponent } from './expense-types/view-edit-expense-type/view-edit-expense-type.component';
import { ListClothTypeComponent } from './cloth-types/list-cloth-type/list-cloth-type.component';
import { AddClothTypeComponent } from './cloth-types/add-cloth-type/add-cloth-type.component';
import { ViewEditClothTypeComponent } from './cloth-types/view-edit-cloth-type/view-edit-cloth-type.component';
import { ListFactoryComponent } from './factories/list-factory/list-factory.component';
import { AddFactoryComponent } from './factories/add-factory/add-factory.component';
import { ViewEditFactoryComponent } from './factories/view-edit-factory/view-edit-factory.component';
import { ProductDetailsComponent } from './products/product-details/product-details.component';
import { AddProductComponent } from './products/add-product/add-product.component';
import { ListProductComponent } from './products/list-product/list-product.component';
import { ListTaskComponent } from './tasks/list-task/list-task.component';
import { AddTaskComponent } from './tasks/add-task/add-task.component';
import { ViewEditClothComponent } from './clothes/view-edit-cloth/view-edit-cloth.component';
import { AddClothComponent } from './clothes/add-cloth/add-cloth.component';
import { ListClothComponent } from './clothes/list-cloth/list-cloth.component';
import { ListSchoolComponent } from './schools/list-school/list-school.component';
import { AddSchoolComponent } from './schools/add-school/add-school.component';
import { SchoolProductsComponent } from './schools/school-products/school-products.component';
import { SchoolSalesComponent } from './schools/school-sales/school-sales.component';
import { BillsComponent } from './bills/bills.component';
import { BillDetailsComponent } from './bills/bill-details/bill-details.component';
import { AddBillComponent } from './bills/add-bill/add-bill.component';
import { BillComponent } from './bills/bill/bill.component';
import { ListProductionsComponent } from './productions/list-productions/list-productions.component';
import { ListProductionsHistoryComponent } from './productions/list-productions-history/list-productions-history.component';
import { AddProductionComponent } from './productions/add-production/add-production.component';
import { ListExpensesComponent } from './expenses/list-expenses/list-expenses.component';
import { AddExpenseComponent } from './expenses/add-expense/add-expense.component';
import { ListEmployeesComponent } from './employees/list-employees/list-employees.component';
import { AddEmployeeComponent } from './employees/add-employee/add-employee.component';
@NgModule({
  declarations: [
    AuditListComponent,
    ListColorComponent,
    AddColorComponent,
    ViewEditColorComponent,
    ListExpenseTypeComponent,
    AddExpenseTypeComponent,
    ViewEditExpenseTypeComponent,
    ListClothTypeComponent,
    AddClothTypeComponent,
    ViewEditClothTypeComponent,
    ListFactoryComponent,
    AddFactoryComponent,
    ViewEditFactoryComponent,
    ProductDetailsComponent,
    AddProductComponent,
    ListProductComponent,
    ListTaskComponent,
    AddTaskComponent,
    ViewEditClothComponent,
    ListClothComponent,
    AddClothComponent,
    ListSchoolComponent,
    AddSchoolComponent,
    SchoolProductsComponent,
    SchoolSalesComponent,
    BillsComponent,
    BillDetailsComponent,
    AddBillComponent,
    BillComponent,
    ListProductionsComponent,
    ListProductionsHistoryComponent,
    AddProductionComponent,
    ListExpensesComponent,
    AddExpenseComponent,
    ListEmployeesComponent,
    AddEmployeeComponent,
  ],
  providers :[ConfirmModalService ],
  imports: [
    AdministrationRoutingModule, CommonModule,FormsModule,
    AlertModule,
    FormsModule,
    PaginationModule.forRoot(),
    ReactiveFormsModule,
    CollapseModule.forRoot(),
    NgbModalModule
    
  ], 
})
export class AdministrationModule { }
