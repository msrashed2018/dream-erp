import { Component, OnInit } from '@angular/core';
import { COLORS, NO_DATA_FOUND, CLOTH_TYPES, PRODUCTS } from '../../../../app-words';
import { ConfirmModalService } from '../../../confirm-modal/confirm-modal.service';
import { Router } from '@angular/router';
import { COLOR_PAGE_SIZE, COLOR_PAGE_SORT ,PAGINATION_MAX_SIZE} from '../../../../app.constants';
import { Product } from '../../../../model/product.model';
import { ProductService } from '../../../../services/administration/product.service';

@Component({
  selector: 'app-list-product',
  templateUrl: './list-product.component.html',
  styleUrls: ['./list-product.component.scss']
})
export class ListProductComponent implements OnInit {
  title = PRODUCTS;
  noDataFound = "";
  errorMessage = "";
  products: Product[];

  //pagination variables
  maxSize: number = PAGINATION_MAX_SIZE;
  totalItems: number = 0;
  currentPage: number = 0;
  numPages: number = 0;
  items: number = 0;
  itemsPerPage : number = COLOR_PAGE_SIZE;

  constructor(
    private productService: ProductService,
    private router: Router,
    private confirmationModalService: ConfirmModalService
  ) {
    
  }

  pageChanged(event: any): void {
    
    this.items = (event.page -1) * this.itemsPerPage ;
    this.refreshData(event.page -1);
  }

  ngOnInit() {
    this.refreshData(this.currentPage);
  }
  refreshData(page) {
    this.productService.retrieveProducts(page, COLOR_PAGE_SIZE, COLOR_PAGE_SORT).subscribe(
      response => {
        this.errorMessage = "";
        this.products = response['content'];
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
        this.errorMessage = error.error.message;
      }
    )
  }

  onDelete(id) {
    this.confirmationModalService.confirm('برجاء التاكيد', 'هل انت متاكد من حذف نوع القماش ')
      .then((confirmed) => {
        if (confirmed) {
          this.productService.deleteProduct(id).subscribe(
            response => {
              this.refreshData(this.currentPage -1);
            },
            error => {
              console.log('oops', error)
              this.errorMessage = error.error.message
            }
          )
        }
      })
  }
  onProductDetails(id) {
    
    this.router.navigate(['administration/products', id])
  }
  onAdd() {
    this.router.navigate(['administration/add-product']);
  }


  updateProduct(id: number, property: string, event: any) {
    const editField = event.target.textContent;
    let product : Product = this.products.find((product) => product.id == id);

    if(property == 'name'){
      product.name = editField;
    }else if (property == 'notes'){
      product.notes = editField;
    }
    this.productService.updateProduct(id,product).subscribe(
      response => {
        // this.refreshData(this.currentPage - 1);
        this.errorMessage = "";
      },
      error => {
        console.log('oops', error);
        this.errorMessage = error.error.message;
      }
    )
    
  }

  changeValue(id: number, property: string, event: any) {
    // const editField = event.target.textContent;
    // console.log("editField = "+editField)
    // console.log("id = "+id)
    // console.log("property = "+property)
  }
}
