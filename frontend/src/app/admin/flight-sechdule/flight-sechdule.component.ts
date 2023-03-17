import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { DateAdapter } from '@angular/material/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminServiceService } from 'src/app/service/admin-service.service';

@Component({
  selector: 'app-flight-sechdule',
  templateUrl: './flight-sechdule.component.html',
  styleUrls: ['./flight-sechdule.component.css']
})
export class FlightSechduleComponent implements OnInit {
  
  constructor(private activateroute:ActivatedRoute, private router:Router, private admin:AdminServiceService) { }
  startTime!:any;
  endTime!:any;
  FlightId!:number;
  sechduleSucess:boolean = false;
  ngOnInit(): void {
      this.activateroute.params.subscribe((res:any)=>{
            if(res.id){
              this.FlightId=res.id;
            }
      })
  } 

  sechduleFlight(){
    this.sechduleSucess = true;
    let sTime = this.getFormat(this.startTime); 
    let eTime = this.getFormat(this.endTime);
    this.admin.sechduleTheflight(this.FlightId,sTime,eTime)
    .subscribe((res)=>{
          this.sechduleSucess = true;
          alert("you flight has been sechduled");
          this.router.navigate(['admin']);
          console.log(res);
    },  
    (error)=>{
      this.sechduleSucess = false;
      console.log(error);
      alert(error['error']['message']);
    })
  }

  

  private getFormat(datetime:any){
    let datepipe: DatePipe = new DatePipe('en-US')
    let formattedDate = datepipe.transform(datetime, 'd-MM-yyyy HH:mm')
    return formattedDate;
  }


}
