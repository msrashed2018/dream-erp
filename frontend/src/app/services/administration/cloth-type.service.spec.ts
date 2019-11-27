import { TestBed } from '@angular/core/testing';

import { ClothTypeService } from './cloth-type.service';

describe('ClothTypeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ClothTypeService = TestBed.get(ClothTypeService);
    expect(service).toBeTruthy();
  });
});
