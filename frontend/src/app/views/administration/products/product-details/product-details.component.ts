import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ProductService } from '../../../../services/administration/product.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ProductDetails } from '../../../../model/product-details.model';
import { ConfirmModalService } from '../../../confirm-modal/confirm-modal.service';
import { ErrorModalService } from '../../../error-modal/error-modal.service';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.scss']
})
export class ProductDetailsComponent implements OnInit {
  product = {};
  productId;
  productDetails: ProductDetails[];
  productDetail: ProductDetails = new ProductDetails();
  isCollapsed: boolean = false;
  iconCollapse: string = 'icon-arrow-up';
  successMessage = "";
  constructor(private formBuilder: FormBuilder,
     private productService: ProductService, 
     private router: Router, 
     private route: ActivatedRoute,
     private confirmationModalService: ConfirmModalService,
     private errorService: ErrorModalService) { }

  ngOnInit() {
    this.route.params.forEach((urlParams) => {
      this.productId = urlParams['id'];
      this.displayProduct();
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

  displayProduct() {
    this.productService.retrieveProduct(this.productId).subscribe(
      response => {
        this.product = response as any;
        this.displayProductDetails();
      },
      error => {
        console.error(error);
        this.errorService.error("ERROR",error.error.message) ;
      }
    )
  }
  displayProductDetails() {
    this.productService.retrieveProductDetails(this.productId).subscribe(
      response => {
        this.productDetails = response as any;
      }, error => {
        console.error(error);
        this.errorService.error("ERROR",error.error.message) ;
      }
    )
  }
  addProductDetails() {
    this.successMessage = ""
    this.productService.addProductDetails(this.productId, this.productDetail).subscribe(
      result => {
        
        this.displayProductDetails();
        this.successMessage = "تم اضافة المقاس بنجاح"
      },
      error => {
        console.error(error);
        if (error.status == 409) {
          this.errorService.error("ERROR","تم تسجيل هذا المقاس من قبل") ;
        } else {
          this.errorService.error("ERROR","تم تسجيل هذا المقاس من قبل") ;
        }
        console.log('oops', error);
      }
    );
  }
  updateProductDetail(productDetailsId: number, property: string, event: any) {
    const editField = event.target.textContent;


    let productDetail : ProductDetails = this.productDetails.find((p) => p.id == productDetailsId);

    if(property == 'size'){
      productDetail.size = editField;
    }else if (property == 'price'){
      productDetail.price = editField;
    }else if (property == 'wholesalePrice'){
      productDetail.wholesalePrice = editField;
    }else if (property == 'notes'){
      productDetail.notes = editField;
    }
    this.productService.updateProductDetails(productDetailsId,productDetail).subscribe(
      response => {
        // this.refreshData(this.currentPage - 1);
        
      },
      error => {
        console.error('oops', error);
        this.errorService.error("ERROR",error.error.message) ;
      }
    )
    
  }
  deleteProductDetails(productDetailId) {
    this.confirmationModalService.confirm('برجاء التاكيد', 'هل انت متاكد من حذف المقاس  ')
      .then((confirmed) => {
        if (confirmed) {
          this.productService.deleteProductDetails(productDetailId).subscribe(
            response => {
              this.displayProductDetails();
            },
            error => {
              console.error('oops', error);
              this.errorService.error("ERROR",error.error.message) ;
            }
          )
        }
      })
  }
  close() {
    this.router.navigateByUrl("/administration/products");
  }
}
