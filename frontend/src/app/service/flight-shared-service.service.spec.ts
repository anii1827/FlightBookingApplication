import { TestBed } from '@angular/core/testing';

import { FlightSharedServiceService } from './flight-shared-service.service';

describe('FlightSharedServiceService', () => {
  let service: FlightSharedServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FlightSharedServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
