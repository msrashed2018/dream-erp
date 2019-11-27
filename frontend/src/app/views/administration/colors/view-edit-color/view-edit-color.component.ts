import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ColorService } from '../../../../services/administration/color.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-view-edit-color',
  templateUrl: './view-edit-color.component.html',
  styleUrls: ['./view-edit-color.component.scss']
})
export class ViewEditColorComponent implements OnInit {
  color = {};
  colorId;
  componentMode;
  disabled: boolean = false;
  successMessage: boolean = false;
  isCollapsed: boolean = false;
  iconCollapse: string = 'icon-arrow-up';
  errorMessage = "";
  constructor(private formBuilder: FormBuilder, private colorService: ColorService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.forEach((urlParams) => {
      this.colorId = urlParams['id'];
      this.componentMode = urlParams['componentMode'];
      this.displayColorDetails();

      if (this.componentMode == "editMode") {
        this.disabled = false;
      } else {
        this.disabled = true;
      }
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
  displayColorDetails() {
    this.colorService.retrieveColor(this.colorId).subscribe(
      response => {
        this.color = response as any;
      }
    )
  }
  onSave() {

    this.colorService.updateColor(this.colorId, this.color).subscribe(
      result => {
        this.router.navigateByUrl("/administration/colors");
      },
      error => {
        if(error.status == 409){
          this.errorMessage = "تم تسجيل هذا اللون من قبل";
        }else{
          this.errorMessage = error.error.message;
        }
        
        console.log('oops', error);
        this.successMessage = false;
      }
    );
  }
  close() {
    this.router.navigateByUrl("/administration/colors");
  }
}
