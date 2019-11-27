import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../../../services/administration/product.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.scss']
})
export class AddProductComponent implements OnInit {
  product = {};
  productDetails = {};
  isCollapsed: boolean = false;
  iconCollapse: string = 'icon-arrow-up';
  errorMessage = "";
  constructor(private productService: ProductService, private router: Router) { }


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

    this.productService.createProduct(this.product).subscribe(
      result => {
        this.router.navigateByUrl("/administration/products");
      },
      error => {
        this.errorMessage = error.error.message;
        console.log('oops', error);
      }
    );
  }
  close() {
    this.router.navigateByUrl("/administration/products");
  }
}
