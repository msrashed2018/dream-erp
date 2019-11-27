import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ClothTypeService } from '../../../../services/administration/cloth-type.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-view-edit-cloth-type',
  templateUrl: './view-edit-cloth-type.component.html',
  styleUrls: ['./view-edit-cloth-type.component.scss']
})
export class ViewEditClothTypeComponent implements OnInit {
  clothType = {};
  clothTypeId;
  componentMode;
  disabled: boolean = false;
  successMessage: boolean = false;
  isCollapsed: boolean = false;
  iconCollapse: string = 'icon-arrow-up';
  errorMessage = "";
  constructor(private formBuilder: FormBuilder, private clothTypeService: ClothTypeService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.forEach((urlParams) => {
      this.clothTypeId = urlParams['id'];
      this.componentMode = urlParams['componentMode'];
      this.displayClothTypeDetails();

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
  displayClothTypeDetails() {
    this.clothTypeService.retrieveClothType(this.clothTypeId).subscribe(
      response => {
        this.clothType = response as any;
      }
    )
  }
  onSave() {

    this.clothTypeService.updateClothType(this.clothTypeId, this.clothType).subscribe(
      result => {
        this.router.navigateByUrl("/administration/clothTypes");
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
    this.router.navigateByUrl("/administration/clothTypes");
  }
}
