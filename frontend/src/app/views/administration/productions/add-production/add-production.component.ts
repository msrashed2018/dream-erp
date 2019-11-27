import { Component, OnInit } from '@angular/core';
import { Production } from '../../../../model/production.model';
import { ProductDetails } from '../../../../model/product-details.model';
import { Product } from '../../../../model/product.model';
import { TokenStorageService } from '../../../../services/authentication/jwt/token-storage.service';
import { FormBuilder } from '@angular/forms';
import { ProductService } from '../../../../services/administration/product.service';
import { Router } from '@angular/router';
import { ConfirmModalService } from '../../../confirm-modal/confirm-modal.service';
import { ErrorModalService } from '../../../error-modal/error-modal.service';
import { PRODUCT_PAGE_SORT } from '../../../../app.constants';
import { ProductionService } from '../../../../services/production.service';

@Component({
  selector: 'app-add-production',
  templateUrl: './add-production.component.html',
  styleUrls: ['./add-production.component.scss']
})
export class AddProductionComponent implements OnInit {

  production: Production = {} as any;
  productSizes: ProductDetails[] = [];
  products: Product[] = [];
  productDetailsNote = "";
  selectedProductId :number;
  selectedSizeId :number;

  isCollapsed: boolean = false;
  iconCollapse: string = 'icon-arrow-up';

  collapsed(event: any): void {
  }

  expanded(event: any): void {
  }
  toggleCollapse(): void {
    this.isCollapsed = !this.isCollapsed;
    this.iconCollapse = this.isCollapsed ? 'icon-arrow-down' : 'icon-arrow-up';
  }

  constructor(
    private tokenStorageService: TokenStorageService,
    private formBuilder: FormBuilder,
    private productService: ProductService,
    private productionService: ProductionService,
    private router: Router,
    private confirmationModalService: ConfirmModalService,
    private errorModalService: ErrorModalService

  ) { }

  ngOnInit() {
    this.getProducts();
  }

  getProducts() {
    this.productService.retrieveAllProducts().subscribe(
      response => {
        this.products = response;
      }, error => {
        this.errorModalService.error("ERROR", error.error.message);
      }
    )
  }

  onProductSelected(productId) {
    if (productId != "" && productId != 0) {
      this.productService.retrieveProductDetails(productId).subscribe(
        response => {
          this.productSizes = response as any;
        }, error => {
          this.errorModalService.error("ERROR", error.error.message);
        }
      )
    } else {
      this.productSizes = [];
    }

  }
  onProductSizeSelected(productDestailId) {
    if (productDestailId != "" && productDestailId != 0) {
      let pDetails: ProductDetails = this.productSizes.find((c) => c.id == this.selectedSizeId);
      this.productDetailsNote = `
      سعر القطعة :
      ${pDetails.price}
       | سعر الجملة :
      ${pDetails.wholesalePrice}
       |
      ${pDetails.notes}
      `
    } else {
      this.productDetailsNote = ""
    }

  }
  onSave(){

    this.production.productSize = this.productSizes.find((c) => c.id == this.selectedSizeId);
    this.productionService.createProduction(this.production).subscribe(
      response =>{
        this.router.navigateByUrl("/administration/productions");
      }, 
      error =>{
        console.log('oops', error);
        this.errorModalService.error("ERROR", error.error.message);
      }
    )
  }
  close(){
    this.router.navigateByUrl("/administration/productions");
  }

}
