import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewEditExpenseTypeComponent } from './view-edit-expense-type.component';

describe('ViewEditExpenseTypeComponent', () => {
  let component: ViewEditExpenseTypeComponent;
  let fixture: ComponentFixture<ViewEditExpenseTypeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewEditExpenseTypeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewEditExpenseTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
