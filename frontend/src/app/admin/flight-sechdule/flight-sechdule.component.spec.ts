import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FlightSechduleComponent } from './flight-sechdule.component';

describe('FlightSechduleComponent', () => {
  let component: FlightSechduleComponent;
  let fixture: ComponentFixture<FlightSechduleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FlightSechduleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FlightSechduleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
