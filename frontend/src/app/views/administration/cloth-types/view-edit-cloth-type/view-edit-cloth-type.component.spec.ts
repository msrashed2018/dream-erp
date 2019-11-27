import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewEditClothTypeComponent } from './view-edit-cloth-type.component';

describe('ViewEditClothTypeComponent', () => {
  let component: ViewEditClothTypeComponent;
  let fixture: ComponentFixture<ViewEditClothTypeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewEditClothTypeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewEditClothTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
