import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddClothTypeComponent } from './add-cloth-type.component';

describe('AddClothTypeComponent', () => {
  let component: AddClothTypeComponent;
  let fixture: ComponentFixture<AddClothTypeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddClothTypeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddClothTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
