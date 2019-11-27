import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListClothTypeComponent } from './list-cloth-type.component';

describe('ListClothTypeComponent', () => {
  let component: ListClothTypeComponent;
  let fixture: ComponentFixture<ListClothTypeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListClothTypeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListClothTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
