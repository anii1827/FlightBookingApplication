import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  isBooking:boolean=false;
  isCancelation:boolean=false;
  isTicketSearch:boolean=false;
  isHistory:boolean=false;

  constructor() { }

  ngOnInit(): void {
  }

  public booking(){
    this.isBooking =!this.isBooking;
    console.log(this.isBooking);
  }

}
