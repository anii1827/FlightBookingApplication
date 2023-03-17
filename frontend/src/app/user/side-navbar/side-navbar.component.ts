import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-side-navbar',
  templateUrl: './side-navbar.component.html',
  styleUrls: ['./side-navbar.component.css']
})
export class SideNavbarComponent implements OnInit {
  isBooking:boolean=false;
  isCancelation:boolean=false;
  isTicketSearch:boolean=false;
  isHistory:boolean=false;
  
  constructor() { 
    
  }

  ngOnInit(): void {
  }

  public booking(){
    this.isBooking =!this.isBooking;
    console.log(this.isBooking);
  }

}
