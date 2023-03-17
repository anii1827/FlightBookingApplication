import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AdminServiceService } from 'src/app/service/admin-service.service';
import { receiveFlight } from '../flightDto';


@Component({
  selector: 'app-all-flights',
  templateUrl: './all-flights.component.html',
  styleUrls: ['./all-flights.component.css']
})
export class AllFlightsComponent implements OnInit {
  datasource!: receiveFlight[];
  constructor(private adminservice:AdminServiceService, private router:Router) { }

  ngOnInit(): void {
      this.adminservice.getall().subscribe((res)=>{
          this.datasource = <receiveFlight[]>res;
          // for(let i of this.datasource){
          //     console.log(i);
          // }
      },  
      (error)=>{
        console.log(error);
        alert(error['error']['message']);
      })

  }

  sechduleflight(id:number){
      this.router.navigate(['admin/sechdule/'+id]);
  }

  blockFlight(id:number){
    this.router.navigate(['admin/block/'+id]);
  }
}
