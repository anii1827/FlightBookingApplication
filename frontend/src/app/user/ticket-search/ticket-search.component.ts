import { Component, OnInit } from '@angular/core';
import { Flight } from 'src/model/FlightDetails';
import { passangerArray, Ticket } from 'src/model/Ticket';
import { passanger } from 'src/model/UserDTO';
import { FlightServiceService } from '../../service/flight-service.service';

@Component({
  selector: 'app-ticket-search',
  templateUrl: './ticket-search.component.html',
  styleUrls: ['./ticket-search.component.css']
})
export class TicketSearchComponent implements OnInit {
  searchsuccess:boolean = false;
  PNR!:string;
  phoneNumber!:string;
  dataSource!:Ticket; 
  constructor(private flightService:FlightServiceService) { }

  ngOnInit(): void {
    
  }
  find(){
    this.searchsuccess = true;
    this.flightService.findTheTicket(this.PNR,this.phoneNumber).subscribe((res)=>{
          this.searchsuccess = false;
          this.dataSource = res;
    },
    (error)=>{
          this.searchsuccess = false;
      console.log(error);
      alert(error['error']['message']);;
    })
  }

}
