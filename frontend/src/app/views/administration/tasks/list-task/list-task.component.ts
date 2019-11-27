import { Component, OnInit } from '@angular/core';
import { NO_DATA_FOUND, TASKS } from '../../../../app-words';
import { ConfirmModalService } from '../../../confirm-modal/confirm-modal.service';
import { Router } from '@angular/router';
import { COLOR_PAGE_SIZE ,PAGINATION_MAX_SIZE, TASK_PAGE_SIZE, TASK_PAGE_SORT} from '../../../../app.constants';
import { Task } from '../../../../model/task.model';
import { TaskService } from '../../../../services/administration/task.service';
import { ErrorModalService } from '../../../error-modal/error-modal.service';

@Component({
  selector: 'app-list-task',
  templateUrl: './list-task.component.html',
  styleUrls: ['./list-task.component.scss']
})
export class ListTaskComponent implements OnInit {
  title = TASKS;
  noDataFound = "";
  tasks: Task[];

  //pagination variables
  totalItems: number = 0;
  currentPage: number = 0;
  numPages: number = 0;
  items: number = 0;
  itemsPerPage : number = COLOR_PAGE_SIZE;

  constructor(
    private taskService: TaskService,
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
    this.taskService.retrieveAllTasks(page, TASK_PAGE_SIZE, TASK_PAGE_SORT).subscribe(
      response => {
        this.tasks = response['content'];
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
          this.taskService.deleteTask(id).subscribe(
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
    this.router.navigate(['administration/add-task'])
  }


  updateTask(id: number, property: string, event: any) {
    const editField = event.target.textContent;
    let task : Task = this.tasks.find((task) => task.id == id);

    if(property == 'name'){
      task.name = editField;
    }else if (property == 'price'){
      task.price = editField;
    }else if (property == 'notes'){
      task.notes = editField;
    }
    this.taskService.updateTask(id,task).subscribe(
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
    
    
}
