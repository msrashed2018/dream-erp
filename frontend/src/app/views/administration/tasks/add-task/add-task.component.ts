import { Component, OnInit } from '@angular/core';
import { TaskService } from '../../../../services/administration/task.service';
import { Router } from '@angular/router';
import { ErrorModalService } from '../../../error-modal/error-modal.service';

@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.scss']
})
export class AddTaskComponent implements OnInit {
  task = {};
  isCollapsed: boolean = false;
  iconCollapse: string = 'icon-arrow-up';
  constructor(
    private taskService: TaskService,
    private router: Router,
    private errorModalService: ErrorModalService) { }


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

    this.taskService.createTask(this.task).subscribe(
      result => {
        this.router.navigateByUrl("/administration/tasks");
      },
      error => {
        this.errorModalService.error("ERROR", error.error.message)
        console.log('oops', error);
      }
    );
  }
  close() {
    this.router.navigateByUrl("/administration/tasks");
  }
}
