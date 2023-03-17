import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { map, Observable, startWith } from 'rxjs';
import { AdminServiceService } from 'src/app/service/admin-service.service';
import { State, states } from 'src/model/Stats';
import { Flight } from '../flightDto';

@Component({
  selector: 'app-flight-register',
  templateUrl: './flight-register.component.html',
  styleUrls: ['./flight-register.component.css']
})
export class FlightRegisterComponent implements OnInit {
  flighDetails!:FormGroup
  sourceStates: Observable<State[]>;
  destinationStates: Observable<State[]>;
  source : State[]=states;
  destination : State[]=states;
  successregister:boolean = false;
  constructor(private router:Router, private adminservice:AdminServiceService, private _formBuilder:FormBuilder) {
    this.flighDetails =  this._formBuilder.group({
      Airline: ['', Validators.required],
      FlightNo: ['', Validators.required],
      Source: ['', Validators.required],
      Destination: ['', Validators.required],
      BusinessSeat: ['', Validators.required],
      NonBusinessSeat: ['', Validators.required],
      BusinessPrice: ['', Validators.required],
      NonBusinessprice: ['', Validators.required],
    })

    this.sourceStates = this.flighDetails.controls['Source'].valueChanges.pipe(
      startWith(''),
      map((state: string) => (state ? this._filterSourceStates(state) : this.source.slice())),
    );

    this.destinationStates = this.flighDetails.controls['Destination'].valueChanges.pipe(
      startWith(''),
      map((state: string) => (state ? this._filterDestinationStates(state) : this.destination.slice())),
    );
   }

   private _filterSourceStates(value: string): State[] {
    const filterValue = value.toLowerCase();
    return this.source.filter(sourceStats => sourceStats.name.toLowerCase().includes(filterValue));
  }

  private _filterDestinationStates(value: string): State[] {
    const filterValue = value.toLowerCase();
    return this.destination.filter(destinationStats => destinationStats.name.toLowerCase().includes(filterValue));
  }


  ngOnInit(): void {
   
  }

  registerFlight(){
    this.successregister = true;
    const flight:Flight = {
    airLine: this.flighDetails.controls['Airline'].value,
    flightNo: this.flighDetails.controls['FlightNo'].value,
    source:this.flighDetails.controls['Source'].value,
    destination:this.flighDetails.controls['Destination'].value,
    totalSeatInBusinessClass:this.flighDetails.controls['BusinessSeat'].value,
    totalSeatInNonBusinessClass: this.flighDetails.controls['BusinessSeat'].value,
    ticketPriceForBusinessClass:this.flighDetails.controls['BusinessPrice'].value,
    ticketPriceForNonBusinessClass: this.flighDetails.controls['NonBusinessprice'].value,
    }
    // console.log(flight);
    this.adminservice.addFlight(flight).subscribe(
      (res)=>{
      this.successregister = false;
      alert("Airline added Sucessfully. check In flights tab");
      this.router.navigate(['admin'])
      // console.log(res);
      },
      (error)=>{
        this.successregister = false;
        alert(error['error']['message'])
      }
      )
  }

}
