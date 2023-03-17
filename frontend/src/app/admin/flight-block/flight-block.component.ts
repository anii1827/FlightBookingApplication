import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminServiceService } from 'src/app/service/admin-service.service';

@Component({
  selector: 'app-flight-block',
  templateUrl: './flight-block.component.html',
  styleUrls: ['./flight-block.component.css']
})
export class FlightBlockComponent implements OnInit {
  blockSuccess: boolean = false;
  FlightId!: number;
  constructor(private router: Router, private activateroute: ActivatedRoute, private adminservice: AdminServiceService) { }

  ngOnInit(): void {
    this.activateroute.params.subscribe((res: any) => {
      if (res.id) {
        this.FlightId = res.id;
      }
    })
  }


  blockFlight() {
    this.blockSuccess = true;
    this.adminservice.blockflight(this.FlightId).subscribe((res) => {
      this.blockSuccess = false;
      let bookingSucessMessage = JSON.parse(JSON.stringify(res))['message'];
      alert(bookingSucessMessage);
      this.router.navigate(['admin']);
      console.log(res);
    },
      (error) => {
        this.blockSuccess = false;
        console.log(error);
        alert(error['error']['message']);
      });
  }
}
