import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewEditClothComponent } from './view-edit-cloth.component';

describe('ViewEditClothComponent', () => {
  let component: ViewEditClothComponent;
  let fixture: ComponentFixture<ViewEditClothComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewEditClothComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewEditClothComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
