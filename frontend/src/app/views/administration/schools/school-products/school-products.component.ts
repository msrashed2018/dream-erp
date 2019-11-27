import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ProductService } from '../../../../services/administration/product.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ProductDetails } from '../../../../model/product-details.model';
import { ConfirmModalService } from '../../../confirm-modal/confirm-modal.service';
import { SCHOOL_PRODUCTS_SIZE, SCHOOL_PRODUCTS_PAGE_SORT, PRODUCT_PAGE_SORT } from '../../../../app.constants';
import { SchoolProduct } from '../../../../model/school-product.model';
import { ErrorModalService } from '../../../error-modal/error-modal.service';
import { SchoolService } from '../../../../services/school.service';
import { StudyLevels } from '../../../../model/study-levels.enum';
import { NO_DATA_FOUND } from '../../../../app-words';
import { Product } from '../../../../model/product.model';
import { School } from '../../../../model/school.model';
import { TokenStorageService } from '../../../../services/authentication/jwt/token-storage.service';

@Component({
  selector: 'app-school-products',
  templateUrl: './school-products.component.html',
  styleUrls: ['./school-products.component.scss']
})
export class SchoolProductsComponent implements OnInit {
  school: School = new School();
  product: Product = new Product();
  schoolProducts: SchoolProduct[] = [];
  schoolProduct: SchoolProduct = new SchoolProduct();
  products: Product[] = [];
  productDetails: ProductDetails[] = [];
  studyLevel = "";
  schoolId = 0;
  selectedProductId = 0;
  selectedSizeId = 0;
  productDetailsNote = ""
  productDetail: ProductDetails = new ProductDetails();
  isCollapsed: boolean = false;
  iconCollapse: string = 'icon-arrow-up';
  noDataFound = "";
  enableAdd: boolean = false;

  isAdmin: boolean = false;
  wholeProductsPrice: number = 0;

  //filter variables
  filteredStudyLevel = "";
  filteredProductName = "";
  filteredDateFrom: string = "";
  filteredDateTo: string = "";
  filteredSize: number = 0;

  //pagination variables
  totalItems: number = 0;
  currentPage: number = 0;
  numPages: number = 0;
  items: number = 0;
  itemsPerPage: number = SCHOOL_PRODUCTS_SIZE;
  successMessage = ''

  constructor(
    private tokenStorageService: TokenStorageService,
    private formBuilder: FormBuilder,
    private productService: ProductService,
    private schoolService: SchoolService,
    private router: Router,
    private route: ActivatedRoute,
    private confirmationModalService: ConfirmModalService,
    private errorModalService: ErrorModalService
  ) { }

  ngOnInit() {
    this.isAdmin = this.tokenStorageService.hasAdminRole()
    this.route.params.forEach((urlParams) => {
      this.schoolId = urlParams['id'];
      this.getSchool();
      this.displaySchoolProducts(this.currentPage);
      this.getProducts();
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
  getTotalPrice() {
    this.schoolService.getSchoolProductsTotalPrice(this.schoolId, this.filteredProductName, this.filteredSize, this.filteredDateFrom, this.filteredDateTo, this.filteredStudyLevel).subscribe(
      response => {
        this.wholeProductsPrice = response as number
      },
      error => {
        console.log('oops', error)
        if (error.error != null) {
          this.errorModalService.error("ERROR", error.error.message);
        } else {
          this.errorModalService.error("ERROR", error.error);
        }
      }
    )
  }
  getSchool() {
    this.schoolService.retrieveSchool(this.schoolId).subscribe(
      response => {
        this.school = response as School;
      }, error => {
        this.errorModalService.error("ERROR", error.error.message);
      }
    )
  }
  displaySchoolProducts(page) {
    if (this.filteredSize == null) {
      this.filteredSize = 0;
    }
    this.schoolService.retrieveSchoolProducts(this.schoolId, this.filteredProductName, this.filteredSize, this.filteredDateFrom, this.filteredDateTo, this.filteredStudyLevel, page, SCHOOL_PRODUCTS_SIZE, SCHOOL_PRODUCTS_PAGE_SORT).subscribe(
      response => {

        this.schoolProducts = response['content'];
        this.totalItems = response['totalElements'];
        if (response['content'].length != 0) {
          this.noDataFound = "";
        } else {
          this.noDataFound = NO_DATA_FOUND;
        }
        this.getTotalPrice();
        // console.log(JSON.stringify(this.schoolProducts));

      },
      error => {
        console.log('oops', error)
        if (error.error != null) {
          this.errorModalService.error("ERROR", error.error.message);
        } else {
          this.errorModalService.error("ERROR", error.error);
        }

      }
    )
  }

  getProducts() {
    this.productService.retrieveAllProducts().subscribe(
      response => {
        this.products = response['content'];
      }, error => {
        this.errorModalService.error("ERROR", error.error.message);
      }
    )
  }
  onProductSizeSelected(productDestailId) {
    // if (productDestailId != "" && productDestailId != 0) {
    //   let pDetails: ProductDetails = this.productDetails.find((c) => c.id == this.selectedSizeId);
    //   this.productDetailsNote = `
    //   الكمية المتوفرة: ${pDetails.amount}
    //   , 
    //   السعر: ${pDetails.price} 
    //   ,
    //   سعر الجملة : ${pDetails.wholesalePrice}
    //   `
    //   this.schoolProduct.specialPrice = pDetails.wholesalePrice
    //   this.schoolProduct.amount = pDetails.amount
    // } else {
    //   this.productDetailsNote = ""
    //   this.schoolProduct.specialPrice = 0
    //   this.schoolProduct.amount = 0
    // }

  }
  onProductSelected(productId) {
    if (productId != "" && productId != 0) {
      this.productService.retrieveProductDetails(productId).subscribe(
        response => {
          this.productDetails = response as any;
        }, error => {
          this.errorModalService.error("ERROR", error.error.message);
        }
      )
    } else {

      this.productDetails = [];
    }
    this.schoolProduct.specialPrice = 0
    this.schoolProduct.amount = 0
  }
  addSchoolProduct() {

    // let pDetails: ProductDetails = this.productDetails.find((c) => c.id == this.selectedSizeId);
    // if (pDetails.amount < this.schoolProduct.amount) {
    //   this.errorModalService.error("ERROR", `
    //   عفوا الكمية المدخلة
      
    //   (  ${this.schoolProduct.amount} قطعة )
    //    اكثر من الكمية المتوفرة
    //   (  ${pDetails.amount} قطعة )
    //   `);
    // } else {
    //   if (this.schoolProduct.specialPrice == 0) {
    //     this.schoolProduct.specialPrice == pDetails.wholesalePrice;
    //   }

    //   this.schoolProduct.size = pDetails.size;
    //   this.schoolProduct.product = this.products.find((c) => c.id == this.selectedProductId);
    //   this.schoolService.addSchoolProduct(this.schoolId, this.schoolProduct).subscribe(
    //     result => {
    //       this.enableAdd = false
    //       this.displaySchoolProducts(0);

    //       this.successMessage = "تم اضافة المقاس بنجاح"
    //     },
    //     error => {
    //       if (error.status == 409) {
    //         this.errorModalService.error("ERROR", "تم تسجيل هذا المقاس من قبل");
    //       } else {
    //         this.errorModalService.error("ERROR", error.error.message);
    //       }
    //       console.log('oops', error);
    //     }
    //   );
    // }


  }

  onDeleteSchoolProduct(schoolProductId) {
    this.confirmationModalService.confirm('برجاء التاكيد', 'هل انت متاكد من حذف المنتج  ')
      .then((confirmed) => {
        if (confirmed) {
          this.schoolService.deleteSchoolProduct(schoolProductId).subscribe(
            response => {
              this.displaySchoolProducts(this.currentPage - 1);
            },
            error => {
              console.log('oops', error)
              this.errorModalService.error("ERROR", error.error.message);
            }
          )
        }
      })
  }
  close() {
    this.router.navigateByUrl("/administration/schools");
  }

  onFilteredDateFromChanged(dateFrom) {
    this.displaySchoolProducts(0);
  }
  onFilteredDateToChanged(dateTo) {
    this.displaySchoolProducts(0);
  }

  onFilteredProductChanged(productId) {
    this.displaySchoolProducts(0);
  }

  onFilteredStudyLevelChanged(studyLevel) {
    this.displaySchoolProducts(0);
  }

  onFilteredSizeChanged(size) {
    this.displaySchoolProducts(0);
  }
}
