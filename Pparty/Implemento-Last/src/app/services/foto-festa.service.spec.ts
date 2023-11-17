import { TestBed } from '@angular/core/testing';

import { FotoFestaService } from './foto-festa.service';

describe('FotoFestaService', () => {
  let service: FotoFestaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FotoFestaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
