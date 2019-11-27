import { Component, OnInit } from '@angular/core';
import { ColorService } from '../../../../services/administration/color.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-add-color',
  templateUrl: './add-color.component.html',
  styleUrls: ['./add-color.component.scss']
})
export class AddColorComponent implements OnInit {
  color = {};
  isCollapsed: boolean = false;
  iconCollapse: string = 'icon-arrow-up';
  errorMessage = "";
  constructor(private colorService: ColorService, private router: Router) { }


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

    this.colorService.createColor(this.color).subscribe(
      result => {
        this.router.navigateByUrl("/administration/colors");
      },
      error => {
        this.errorMessage = error.error.message;
        console.log('oops', error);
      }
    );
  }
  close() {
    this.router.navigateByUrl("/administration/colors");
  }
}