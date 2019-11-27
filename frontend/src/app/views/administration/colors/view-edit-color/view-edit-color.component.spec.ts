import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewEditColorComponent } from './view-edit-color.component';

describe('ViewEditColorComponent', () => {
  let component: ViewEditColorComponent;
  let fixture: ComponentFixture<ViewEditColorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewEditColorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewEditColorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
