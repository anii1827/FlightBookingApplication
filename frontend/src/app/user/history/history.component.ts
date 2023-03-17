import { Component, OnInit } from '@angular/core';
import { passangerArray, Ticket } from 'src/model/Ticket';
import { FlightServiceService } from '../../service/flight-service.service';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {
  historySuccees:boolean=false;
  phoneNumber!:string;
  dataSource!:Ticket[];
  constructor(private flightservice:FlightServiceService) { }

  ngOnInit(): void {
  }

  find(){
      if(this.phoneNumber==""){
        alert("please provide the phone number");
      }
      else{
        this.historySuccees = true;
        this.flightservice.getHistory(this.phoneNumber).subscribe((res)=>{
          this.historySuccees = false;
          this.dataSource = <Ticket[]>res;
      },
      (error)=>{
        this.historySuccees=false;
        alert(error['error']['message']);
        this.phoneNumber='';
      }
      );
      }
  }
}
