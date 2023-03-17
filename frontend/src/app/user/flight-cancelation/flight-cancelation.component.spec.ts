import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FlightCancelationComponent } from './flight-cancelation.component';

describe('FlightCancelationComponent', () => {
  let component: FlightCancelationComponent;
  let fixture: ComponentFixture<FlightCancelationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FlightCancelationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FlightCancelationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
