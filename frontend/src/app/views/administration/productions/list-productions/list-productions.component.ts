import { Component, OnInit } from '@angular/core';
import { PRODUCTION_PAGE_SIZE, PRODUCTION_PAGE_SORT } from '../../../../app.constants';
import { Production } from '../../../../model/production.model';
import { ErrorModalService } from '../../../error-modal/error-modal.service';
import { ConfirmModalService } from '../../../confirm-modal/confirm-modal.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { TokenStorageService } from '../../../../services/authentication/jwt/token-storage.service';
import { ProductionService } from '../../../../services/production.service';
import { NO_DATA_FOUND, PRODUCTION } from '../../../../app-words';
import { ProductService } from '../../../../services/administration/product.service';
import { Product } from '../../../../model/product.model';

@Component({
  selector: 'app-list-productions',
  templateUrl: './list-productions.component.html',
  styleUrls: ['./list-productions.component.scss']
})
export class ListProductionsComponent implements OnInit {
  title = PRODUCTION;
  productions: Production[] = [];
  products: Product[] = [];
  isCollapsed: boolean = false;
  iconCollapse: string = 'icon-arrow-up';
  noDataFound = "";
  isAdmin: boolean = false;

  //filter variables
  filteredProductName = "";
  filteredSize: number = 0;

  //pagination variables
  totalItems: number = 0;
  currentPage: number = 0;
  numPages: number = 0;
  items: number = 0;
  itemsPerPage: number = PRODUCTION_PAGE_SIZE;
  constructor(
    private tokenStorageService: TokenStorageService,
    private formBuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private productService: ProductService,
    private productionService: ProductionService,
    private confirmationModalService: ConfirmModalService,
    private errorModalService: ErrorModalService


  ) { }

  ngOnInit() {
    
    this.isAdmin = this.tokenStorageService.hasAdminRole();
    this.getProducts();
    this.refreshData(this.currentPage);
  }
  collapsed(event: any): void {
  }

  expanded(event: any): void {
  }
  toggleCollapse(): void {
    this.isCollapsed = !this.isCollapsed;
    this.iconCollapse = this.isCollapsed ? 'icon-arrow-down' : 'icon-arrow-up';
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
  refreshData(page) {

    if (this.filteredSize == null) {
      this.filteredSize = 0;
    }
    this.productionService.retrieveProductions(this.filteredProductName, this.filteredSize, page, PRODUCTION_PAGE_SIZE, PRODUCTION_PAGE_SORT).subscribe(
      response => {
        
        this.productions = response['content'];
        this.totalItems = response['totalElements'];
        if (response['content'].length != 0) {
          this.noDataFound = "";
        } else {
          this.noDataFound = NO_DATA_FOUND;
        }
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

  onFilteredProductChanged(productName) {
    this.refreshData(0);
  }

  onFilteredSizeChanged(productSize) {
    this.refreshData(0);
  }

  onAddProdution() {
    this.router.navigate(['administration/add-production'])
  }
  onAdd() {
    this.router.navigate(['administration/add-production']);
  }


  onDelete(id) {
    this.confirmationModalService.confirm('برجاء التاكيد', 'هل انت متاكد من حذف المنتج  ')
      .then((confirmed) => {
        if (confirmed) {
          this.productionService.deleteProduction(id).subscribe(
            response => {
              this.refreshData(this.currentPage -1);
            },
            error => {
              console.log('oops', error)
              this.errorModalService.error("ERROR", error.error);
            }
          )
        }
      })
  }
}
