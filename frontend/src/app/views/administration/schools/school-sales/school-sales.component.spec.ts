import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SchoolSalesComponent } from './school-sales.component';

describe('SchoolSalesComponent', () => {
  let component: SchoolSalesComponent;
  let fixture: ComponentFixture<SchoolSalesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SchoolSalesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SchoolSalesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
