import { Component, OnInit } from '@angular/core';
import { SchoolService } from '../../../../services/school.service';
import { Router } from '@angular/router';
import { ErrorModalService } from '../../../error-modal/error-modal.service';

@Component({
  selector: 'app-add-school',
  templateUrl: './add-school.component.html',
  styleUrls: ['./add-school.component.scss']
})
export class AddSchoolComponent implements OnInit {
  school = {};
  isCollapsed: boolean = false;
  iconCollapse: string = 'icon-arrow-up';
  constructor(
    private schoolService: SchoolService,
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

    this.schoolService.createSchool(this.school).subscribe(
      result => {
        this.router.navigateByUrl("/administration/schools");
      },
      error => {
        this.errorModalService.error("ERROR", error.error.message)
        console.log('oops', error);
      }
    );
  }
  close() {
    this.router.navigateByUrl("/administration/schools");
  }
}
