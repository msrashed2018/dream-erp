import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewEditFactoryComponent } from './view-edit-factory.component';

describe('ViewEditFactoryComponent', () => {
  let component: ViewEditFactoryComponent;
  let fixture: ComponentFixture<ViewEditFactoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewEditFactoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewEditFactoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
