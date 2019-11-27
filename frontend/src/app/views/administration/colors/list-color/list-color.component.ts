import { Component, OnInit } from '@angular/core';
import { COLORS, NO_DATA_FOUND } from '../../../../app-words';
import { ConfirmModalService } from '../../../confirm-modal/confirm-modal.service';
import { Router } from '@angular/router';
import { COLOR_PAGE_SIZE, COLOR_PAGE_SORT ,PAGINATION_MAX_SIZE} from '../../../../app.constants';
import { Color } from '../../../../model/color.model';
import { ColorService } from '../../../../services/administration/color.service';

@Component({
  selector: 'app-list-color',
  templateUrl: './list-color.component.html',
  styleUrls: ['./list-color.component.scss']
})
export class ListColorComponent implements OnInit {
  title = COLORS;
  noDataFound = "";
  errorMessage = "";
  colors: Color[];

  //pagination variables
  maxSize: number = PAGINATION_MAX_SIZE;
  totalItems: number = 0;
  currentPage: number = 0;
  numPages: number = 0;
  items: number = 0;
  itemsPerPage : number = COLOR_PAGE_SIZE;

  constructor(
    private colorService: ColorService,
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
    this.colorService.retrieveAllColors(page, COLOR_PAGE_SIZE, COLOR_PAGE_SORT).subscribe(
      response => {
        this.errorMessage = "";
        this.colors = response['content'];
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
    this.confirmationModalService.confirm('برجاء التاكيد', 'هل انت متاكد من حذف اللون ')
      .then((confirmed) => {
        if (confirmed) {
          this.colorService.deleteColor(id).subscribe(
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
  onEdit(id) {
    this.router.navigate(['administration/colors', id, { componentMode: "editMode" }])
  }
  onAdd() {
    this.router.navigate(['administration/add-color'])
  }

}
