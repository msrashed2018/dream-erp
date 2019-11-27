import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListClothComponent } from './list-cloth.component';

describe('ListClothComponent', () => {
  let component: ListClothComponent;
  let fixture: ComponentFixture<ListClothComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListClothComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListClothComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
