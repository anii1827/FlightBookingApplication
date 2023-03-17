import { Component, OnInit } from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import { Router } from '@angular/router';
import { FlightServiceService } from '../../service/flight-service.service';

@Component({
  selector: 'app-flight-cancelation',
  templateUrl: './flight-cancelation.component.html',
  styleUrls: ['./flight-cancelation.component.css']
})
export class FlightCancelationComponent implements OnInit {
  PhoneNumber!:string;
  PNR!:string;
  cancelSucessMessage!:string;
  cancelSuccess:boolean = false;
  constructor(private router:Router, private flightService:FlightServiceService, public dialog: MatDialog){}
  ngOnInit(): void {
    
  }

  cancelFlight(){
    this.cancelSuccess=true;
    // alert(this.cancelSucessMessage);
    // this.router.navigate(['/bookTicket']);
      this.flightService.cancelTheFlight(this.PNR,this.PhoneNumber).subscribe(
        (res)=>{
          this.cancelSucessMessage = JSON.parse(JSON.stringify(res))['message'];
          this.cancelSuccess=true;
          alert(this.cancelSucessMessage);
          this.router.navigate(['/bookTicket']);
      }, 
      (error)=>{
        console.log(error);
        alert(error['error']['message']);;
      });
  }

  openDialog() {
    this.dialog.open(DialogElementsExampleDialog);
  }

}
@Component({
  selector: 'dialog-elements-example-dialog',
  templateUrl: 'dilog.html',
})
export class DialogElementsExampleDialog {}

