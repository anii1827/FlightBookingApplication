import { Injectable } from '@angular/core';
import { Flight } from 'src/model/FlightDetails';

@Injectable({
  providedIn: 'root'
})
export class FlightSharedServiceService {
  flight!:Flight;

  constructor() { }

  public setFlight(p:Flight){
      this.flight = p;
  }

  public  getFlight(): Flight{
    return this.flight;
  }
}
