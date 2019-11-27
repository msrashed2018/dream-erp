import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListProductionsComponent } from './list-productions.component';

describe('ListProductionsComponent', () => {
  let component: ListProductionsComponent;
  let fixture: ComponentFixture<ListProductionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListProductionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListProductionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
