import { TestBed } from '@angular/core/testing';

import { ClothService } from './cloth.service';

describe('ClothService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ClothService = TestBed.get(ClothService);
    expect(service).toBeTruthy();
  });
});
