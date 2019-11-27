import { Component, OnInit } from '@angular/core';
import { NO_DATA_FOUND, TASKS } from '../../../../app-words';
import { ConfirmModalService } from '../../../confirm-modal/confirm-modal.service';
import { Router } from '@angular/router';
import { COLOR_PAGE_SIZE ,PAGINATION_MAX_SIZE, TASK_PAGE_SIZE, TASK_PAGE_SORT, COLOR_PAGE_SORT, CLOTH_TYPE_PAGE_SORT, CLOTH_PAGE_SIZE, CLOTH_PAGE_SORT} from '../../../../app.constants';
import { Cloth } from '../../../../model/cloth.model';
import { ClothService } from '../../../../services/cloth.service';
import { ErrorModalService } from '../../../error-modal/error-modal.service';
import { ColorService } from '../../../../services/administration/color.service';
import { ClothTypeService } from '../../../../services/administration/cloth-type.service';
import { ClothType } from '../../../../model/cloth-type.model';
import { Color } from '../../../../model/color.model';

@Component({
  selector: 'app-list-cloth',
  templateUrl: './list-cloth.component.html',
  styleUrls: ['./list-cloth.component.scss']
})
export class ListClothComponent implements OnInit {
  title = TASKS;
  noDataFound = "";
  cloths: Cloth[];
  clothTypes: ClothType[];
  selectedClothTypeId : number = 0;
  selectedColorId : number = 0;
  colors: Color[];
  dateFrom : string = "";
  dateTo : string = "";

  //pagination variables
  maxSize: number = PAGINATION_MAX_SIZE;
  totalItems: number = 0;
  currentPage: number = 0;
  numPages: number = 0;
  items: number = 0;
  itemsPerPage : number = COLOR_PAGE_SIZE;

  constructor(
    private clothService: ClothService,
    private colorService: ColorService,
    private clothTypeService: ClothTypeService,
    private router: Router,
    private confirmationModalService: ConfirmModalService,
    private errorModalService: ErrorModalService
  ) {
    
  }

  pageChanged(event: any): void {
    this.items = (event.page -1) * this.itemsPerPage ;
    this.refreshData(event.page -1);
  }

  ngOnInit() {
    this.refreshData(this.currentPage);
    this.getClothTypes()
    this.getColors();
  }
  refreshData(page) {

    this.clothService.retrieveCloths(this.selectedClothTypeId, this.dateFrom, this.dateTo,this.selectedColorId, page, CLOTH_PAGE_SIZE, CLOTH_PAGE_SORT).subscribe(
      response => {
        this.cloths = response['content'];
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
        this.errorModalService.error('ERROR', error.error.message);
      }
    )
  }

  onDelete(id) {
    this.confirmationModalService.confirm('برجاء التاكيد', 'هل انت متاكد من حذف نوع المهمة ')
      .then((confirmed) => {
        if (confirmed) {
          this.clothService.deleteCloth(id).subscribe(
            response => {
              this.refreshData(this.currentPage -1);
            },
            error => {
              console.log('oops', error)
              this.errorModalService.error('ERROR', error.error.message);
            }
          )
        }
      })
  }
  onAdd() {
    this.router.navigate(['administration/add-cloth'])
  }

  updateCloth(id: number, property: string, event: any) {
    const editField = event.target.textContent;
    let cloth : Cloth = this.cloths.find((cloth) => cloth.id == id);

    if(property == 'name'){
      // cloth.name = editField;
    }else if (property == 'price'){
      cloth.notes = editField;
    }else if (property == 'notes'){
      cloth.notes = editField;
    }
    this.clothService.updateCloth(id,cloth).subscribe(
      response => {
        // this.refreshData(this.currentPage - 1);
      },
      error => {
        console.log('oops', error);
        this.refreshData(this.currentPage - 1);
        this.errorModalService.error('ERROR', error.error.message);
      }
    )
    
  }

  changeValue(id: number, property: string, event: any) {
    
  }

  getColors(){
    this.colorService.retrieveAllColors(0, 100, COLOR_PAGE_SORT).subscribe(
      response => {
        this.colors = response['content'];
      },
      error => {
        console.log('oops', error);
        this.errorModalService.error("ERROR",error.error.message)
      }
    )
  }

  getClothTypes(){
    this.clothTypeService.retrieveAllClothTypes(0, 100, CLOTH_TYPE_PAGE_SORT).subscribe(
      response => {
        this.clothTypes = response['content'];
      },
      error => {
        console.log('oops', error);
        this.errorModalService.error("ERROR",error.error.message)
      }
    )
  }
  
  onClothTypeChanged(clothType){
    this.refreshData(0);
    
  }
  onDateFromChanged(dateFrom){
    this.refreshData(0);
  }
  onDateToChanged(dateTo){
    this.refreshData(0);
  }
  onColorChanged(color){
    this.refreshData(0);
  }
}
