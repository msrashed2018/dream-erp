import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Color } from '../../../../model/color.model';
import { Cloth } from '../../../../model/cloth.model';
import { ClothService } from '../../../../services/cloth.service';
import { ErrorModalService } from '../../../error-modal/error-modal.service';
import { ClothType } from '../../../../model/cloth-type.model';

@Component({
  selector: 'app-add-cloth',
  templateUrl: './add-cloth.component.html',
  styleUrls: ['./add-cloth.component.scss']
})
export class AddClothComponent implements OnInit {
  cloth : Cloth = new Cloth
  isCollapsed: boolean = false;
  iconCollapse: string = 'icon-arrow-up';
  selectedClothTypeId: number = 0;
  selectedColorId: number = 0;

  constructor(
    private clothService: ClothService,
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
    let clothType = new ClothType
    clothType.id = this.selectedClothTypeId;
    this.cloth.clothType = clothType;

    let color = new Color
    color.id = this.selectedColorId;
    this.cloth.color = color;

    this.clothService.createCloth(this.cloth).subscribe(
      result => {
        this.router.navigateByUrl("/administration/cloths");
      },
      error => {
        this.errorModalService.error("ERROR", error.error.message)
        console.log('oops', error);
      }
    );
  }
  close() {
    this.router.navigateByUrl("/administration/cloths");
  }
}
