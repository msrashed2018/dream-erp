import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListProductionsHistoryComponent } from './list-productions-history.component';

describe('ListProductionsHistoryComponent', () => {
  let component: ListProductionsHistoryComponent;
  let fixture: ComponentFixture<ListProductionsHistoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListProductionsHistoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListProductionsHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
