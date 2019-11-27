import { Component, OnInit } from '@angular/core';
import { NO_DATA_FOUND, TASKS, SCHOOLS } from '../../../../app-words';
import { ConfirmModalService } from '../../../confirm-modal/confirm-modal.service';
import { Router } from '@angular/router';
import { COLOR_PAGE_SIZE ,PAGINATION_MAX_SIZE, TASK_PAGE_SIZE, TASK_PAGE_SORT} from '../../../../app.constants';
import { School } from '../../../../model/school.model';
import { SchoolService } from '../../../../services/school.service';
import { ErrorModalService } from '../../../error-modal/error-modal.service';

@Component({
  selector: 'app-list-school',
  templateUrl: './list-school.component.html',
  styleUrls: ['./list-school.component.scss']
})
export class ListSchoolComponent implements OnInit {
  title = SCHOOLS;
  noDataFound = "";
  schools: School[];
  schoolName : string = ""
  //pagination variables
  maxSize: number = PAGINATION_MAX_SIZE;
  totalItems: number = 0;
  currentPage: number = 0;
  numPages: number = 0;
  items: number = 0;
  itemsPerPage : number = COLOR_PAGE_SIZE;

  constructor(
    private schoolService: SchoolService,
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
  }
  refreshData(page) {
    this.schoolService.retrieveSchools(this.schoolName, page, TASK_PAGE_SIZE, TASK_PAGE_SORT).subscribe(
      response => {
        this.schools = response['content'];
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
    this.confirmationModalService.confirm('برجاء التاكيد', 'هل انت متاكد من حذف المدرسة')
      .then((confirmed) => {
        if (confirmed) {
          this.schoolService.deleteSchool(id).subscribe(
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
    this.router.navigate(['administration/add-school'])
  }
  
  updateSchool(id: number, property: string, event: any) {
    const editField = event.target.textContent;
    let school : School = this.schools.find((school) => school.id == id);

    if(property == 'name'){
      school.name = editField;
    }else if (property == 'owner'){
      school.owner = editField;
    }else if (property == 'mobile'){
      school.mobile = editField;
    }else if (property == 'address'){
      school.address = editField;
    }else if (property == 'notes'){
      school.notes = editField;
    }
    this.schoolService.updateSchool(id,school).subscribe(
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

  onSchoolProducts(schoolId){
    console.log(schoolId)
    this.router.navigate(['administration/schools',schoolId,'products'])
  }


  onSchoolSales(schoolId){
    console.log(schoolId)
    this.router.navigate(['administration/schools',schoolId,'sales'])
  }
}
