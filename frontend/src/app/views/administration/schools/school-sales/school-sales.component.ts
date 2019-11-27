import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ProductService } from '../../../../services/administration/product.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ProductDetails } from '../../../../model/product-details.model';
import { ConfirmModalService } from '../../../confirm-modal/confirm-modal.service';
import { SCHOOL_PRODUCTS_SIZE, SCHOOL_PRODUCTS_PAGE_SORT, PRODUCT_PAGE_SORT } from '../../../../app.constants';
import { SchoolSales } from '../../../../model/school-sales.model';
import { ErrorModalService } from '../../../error-modal/error-modal.service';
import { SchoolService } from '../../../../services/school.service';
import { StudyLevels } from '../../../../model/study-levels.enum';
import { NO_DATA_FOUND } from '../../../../app-words';
import { Product } from '../../../../model/product.model';
import { School } from '../../../../model/school.model';
import { TokenStorageService } from '../../../../services/authentication/jwt/token-storage.service';

@Component({
  selector: 'app-school-sales',
  templateUrl: './school-sales.component.html',
  styleUrls: ['./school-sales.component.scss']
})
export class SchoolSalesComponent implements OnInit {
  school: School = new School();
  product: Product = new Product();
  schoolSales: SchoolSales[] = [];
  schoolSale: SchoolSales = new SchoolSales();
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

  isAdmin : boolean = false;
  wholeProductsPrice : number = 0;

//filter variables
  filteredStudyLevel = "";
  filteredProductName = "";
  filteredDateFrom : string = "";
  filteredDateTo : string = "";
  filteredSize : number= 0;

  //pagination variables
  totalItems: number = 0;
  currentPage: number = 0;
  numPages: number = 0;
  items: number = 0;
  itemsPerPage: number = SCHOOL_PRODUCTS_SIZE;
  successMessage = ''

  constructor(
    private tokenStorageService : TokenStorageService,
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
      this.displaySchoolSales(this.currentPage);
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
  getTotalPrice(){
    this.schoolService.getSchoolSalesTotalPrice(this.schoolId, this.filteredProductName, this.filteredSize, this.filteredDateFrom, this.filteredDateTo, this.filteredStudyLevel).subscribe(
      response =>{
        console.log(response);
        
        this.wholeProductsPrice = response as number
      },
      error=>{
        console.log('oops', error)
        if( error.error != null){
          this.errorModalService.error("ERROR", error.error.message);
        }else{
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
  displaySchoolSales(page) {
    if( this.filteredSize == null){
      this.filteredSize = 0;  
    }
    this.schoolService.retrieveSchoolSales(this.schoolId, this.filteredProductName, this.filteredSize, this.filteredDateFrom, this.filteredDateTo, this.filteredStudyLevel, page, SCHOOL_PRODUCTS_SIZE, SCHOOL_PRODUCTS_PAGE_SORT).subscribe(
      response => {
        
        this.schoolSales = response['content'];
        this.totalItems = response['totalElements'];
        if (response['content'].length != 0) {
          this.noDataFound = "";
        } else {
          this.noDataFound = NO_DATA_FOUND;
        }
        this.getTotalPrice();
        // console.log(JSON.stringify(this.schoolSales));
        
      },
      error => {
        console.log('oops', error)
        if( error.error != null){
          this.errorModalService.error("ERROR", error.error.message);
        }else{
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
  onProductSizeSelected(productDestailId){
    if (productDestailId != "" && productDestailId != 0 ) {
      let pDetails: ProductDetails= this.productDetails.find((c) => c.id == this.selectedSizeId);
      this.productDetailsNote = `
      السعر: ${pDetails.price} 
      ,
      سعر الجملة : ${pDetails.wholesalePrice}
      `
      this.schoolSale.specialPrice = pDetails.wholesalePrice
      // this.schoolSale.amount = pDetails.amount
    }else{
      this.productDetailsNote = ""
      this.schoolSale.specialPrice = 0
    this.schoolSale.amount = 0
    }
    
  }
  onProductSelected(productId) {
    if (productId != "" && productId != 0 ) {
      this.productService.retrieveProductDetails(productId).subscribe(
        response => {
          this.productDetails = response as any;
        }, error => {
          this.errorModalService.error("ERROR", error.error.message);
        }
      )
    }else{

      this.productDetails = [];
    }

    this.schoolSale.specialPrice = 0
    this.schoolSale.amount = 0
  }
  addSchoolSales() {

    // let pDetails: ProductDetails = this.productDetails.find((c) => c.id == this.selectedSizeId);
    // if(pDetails.amount < this.schoolSale.amount){
    //   this.errorModalService.error("ERROR", `
    //   عفوا الكمية المدخلة
      
    //   (  ${this.schoolSale.amount} قطعة )
    //    اكثر من الكمية المتوفرة
    //   (  ${pDetails.amount} قطعة )
    //   `);
    // }else{
    //   if(this.schoolSale.specialPrice == 0 ){
    //     this.schoolSale.specialPrice == pDetails.wholesalePrice;
    //   }

    //   this.schoolSale.size = pDetails.size;
    //   this.schoolSale.product = this.products.find((c) => c.id == this.selectedProductId);
    //   this.schoolService.addSchoolSales(this.schoolId, this.schoolSale).subscribe(
    //     result => {
    //       this.enableAdd = false
    //       this.displaySchoolSales(0);
          
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

  onDeleteSchoolSales(schoolSaleId) {
    this.confirmationModalService.confirm('برجاء التاكيد', 'هل انت متاكد من حذف المنتج  ')
      .then((confirmed) => {
        if (confirmed) {
          this.schoolService.deleteSchoolSales(schoolSaleId).subscribe(
            response => {
              this.displaySchoolSales(this.currentPage - 1);
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

  onFilteredDateFromChanged(dateFrom){
    this.displaySchoolSales(0);
  }
  onFilteredDateToChanged(dateTo){
    this.displaySchoolSales(0);
  }

  onFilteredProductChanged(productId){
    this.displaySchoolSales(0);
  }

  onFilteredStudyLevelChanged(studyLevel){
    this.displaySchoolSales(0);
  }

  onFilteredSizeChanged(size){
    this.displaySchoolSales(0);
  }
}
