import { Component, OnInit } from '@angular/core';
import { ClothTypeService } from '../../../../services/administration/cloth-type.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-add-cloth-type',
  templateUrl: './add-cloth-type.component.html',
  styleUrls: ['./add-cloth-type.component.scss']
})
export class AddClothTypeComponent implements OnInit {
  clothType = {};
  isCollapsed: boolean = false;
  iconCollapse: string = 'icon-arrow-up';
  errorMessage = "";
  constructor(private clothTypeService: ClothTypeService, private router: Router) { }


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

    this.clothTypeService.createClothType(this.clothType).subscribe(
      result => {
        this.router.navigateByUrl("/administration/clothTypes");
      },
      error => {
        this.errorMessage = error.error.message;
        console.log('oops', error);
      }
    );
  }
  close() {
    this.router.navigateByUrl("/administration/clothTypes");
  }
}
