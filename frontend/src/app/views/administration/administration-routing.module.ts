import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListColorComponent } from './colors/list-color/list-color.component';
import { AddColorComponent } from './colors/add-color/add-color.component';
import { ViewEditColorComponent } from './colors/view-edit-color/view-edit-color.component';
import { ListClothTypeComponent } from './cloth-types/list-cloth-type/list-cloth-type.component';
import { AddClothTypeComponent } from './cloth-types/add-cloth-type/add-cloth-type.component';
import { ViewEditClothTypeComponent } from './cloth-types/view-edit-cloth-type/view-edit-cloth-type.component';
import { ViewEditExpenseTypeComponent } from './expense-types/view-edit-expense-type/view-edit-expense-type.component';
import { AddExpenseTypeComponent } from './expense-types/add-expense-type/add-expense-type.component';
import { ListExpenseTypeComponent } from './expense-types/list-expense-type/list-expense-type.component';
import { ListProductComponent } from './products/list-product/list-product.component';
import { AddProductComponent } from './products/add-product/add-product.component';
import { ProductDetailsComponent } from './products/product-details/product-details.component';
import { ListTaskComponent } from './tasks/list-task/list-task.component';
import { AddTaskComponent } from './tasks/add-task/add-task.component';
import { ListClothComponent } from './clothes/list-cloth/list-cloth.component';
import { AddClothComponent } from './clothes/add-cloth/add-cloth.component';
import { ViewEditClothComponent } from './clothes/view-edit-cloth/view-edit-cloth.component';
import { ListSchoolComponent } from './schools/list-school/list-school.component';
import { AddSchoolComponent } from './schools/add-school/add-school.component';
import { SchoolProductsComponent } from './schools/school-products/school-products.component';
import { SchoolSalesComponent } from './schools/school-sales/school-sales.component';
import { BillsComponent } from './bills/bills.component';
import { ListProductionsComponent } from './productions/list-productions/list-productions.component';
import { AddProductionComponent } from './productions/add-production/add-production.component';
import { ListExpensesComponent } from './expenses/list-expenses/list-expenses.component';
import { AddExpenseComponent } from './expenses/add-expense/add-expense.component';

const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Administration'
    },
    children: [
      {
        path: 'schools',
        component: ListSchoolComponent,
        data: {
          title: 'schools'
        }
      },
      {
        path: 'schools/:id/products',
        component: SchoolProductsComponent,
        data: {
          title: 'school-products'
        }
      },
      {
        path: 'schools/:id/sales',
        component: SchoolSalesComponent,
        data: {
          title: 'school-sales'
        }
      },
      {
        path: 'add-school',
        component: AddSchoolComponent,
        data: {
          title: 'Add school'
        }
      },
      {
        path: 'products',
        component: ListProductComponent,
        data: {
          title: 'Products'
        }
      },
      {
        path: 'add-product',
        component: AddProductComponent,
        data: {
          title: 'Add product'
        }
      },
      {
        path: 'products/:id',
        component: ProductDetailsComponent,
        data: {
          title: 'product-details'
        }
      },
      {
        path: 'clothes',
        component: ListClothComponent,
        data: {
          title: 'clothes'
        }
      },
      {
        path: 'add-cloth',
        component: AddClothComponent,
        data: {
          title: 'Add cloth'
        }
      },
      {
        path: 'clothes/:id',
        component: ViewEditClothComponent,
        data: {
          title: 'View-Edit cloth'
        }
      },
      {
        path: 'colors',
        component: ListColorComponent,
        data: {
          title: 'Colors'
        }
      },
      {
        path: 'add-color',
        component: AddColorComponent,
        data: {
          title: 'Add Color'
        }
      },
      {
        path: 'colors/:id',
        component: ViewEditColorComponent,
        data: {
          title: 'View-Edit color'
        }
      },
      {
        path: 'clothTypes',
        component: ListClothTypeComponent,
        data: {
          title: 'clothTypes'
        }
      },
      {
        path: 'add-clothType',
        component: AddClothTypeComponent,
        data: {
          title: 'Add clothTypeslor'
        }
      },
      {
        path: 'clothTypes/:id',
        component: ViewEditClothTypeComponent,
        data: {
          title: 'View-Edit clothTypes'
        }
      },
      {
        path: 'expenseTypes',
        component: ListExpenseTypeComponent,
        data: {
          title: 'expenseTypes'
        }
      },
      {
        path: 'add-expenseType',
        component: AddExpenseTypeComponent,
        data: {
          title: 'Add expenseTypeslor'
        }
      },
      {
        path: 'expenseTypes/:id',
        component: ViewEditExpenseTypeComponent,
        data: {
          title: 'View-Edit expenseTypes'
        }
      },
      {
        path: 'tasks',
        component: ListTaskComponent,
        data: {
          title: 'Tasks'
        }
      },
      {
        path: 'add-task',
        component: AddTaskComponent,
        data: {
          title: 'Add Task'
        }
      },
      {
        path: 'bills',
        component: BillsComponent,
        data: {
          title: 'Bills'
        }
      },
      {
        path: 'productions',
        component: ListProductionsComponent,
        data: {
          title: 'Productions'
        }
      },
      {
        path: 'add-production',
        component: AddProductionComponent,
        data: {
          title: 'Add To Production'
        }
      },
      {
        path: 'expenses',
        component: ListExpensesComponent,
        data: {
          title: 'expenses'
        }
      },
      {
        path: 'add-expense',
        component: AddExpenseComponent,
        data: {
          title: 'Add To Expenses'
        }
      }
    ]
  }
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdministrationRoutingModule { }
