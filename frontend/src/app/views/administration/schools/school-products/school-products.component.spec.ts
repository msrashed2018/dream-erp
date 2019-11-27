import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SchoolProductsComponent } from './school-products.component';

describe('SchoolProductsComponent', () => {
  let component: SchoolProductsComponent;
  let fixture: ComponentFixture<SchoolProductsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SchoolProductsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SchoolProductsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
