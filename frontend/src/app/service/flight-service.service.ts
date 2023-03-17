import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { ConditionalExpr } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { map } from 'rxjs';
import { Flight } from 'src/model/FlightDetails';
import { Ticket } from 'src/model/Ticket';
import { UserDTO } from 'src/model/UserDTO';

@Injectable({
  providedIn: 'root'
})
export class FlightServiceService {
  url: string = "http://"+localStorage.getItem('HOST')+":8989/user/v1.0/api";

  constructor(private http: HttpClient) { }

  fetchFlight(from:string, to:string, searchdate:any){
        let queryParams = new HttpParams();
        queryParams = queryParams.append("source", from).append("destination",to).append("date", searchdate);
        return this.http.get<Flight[]>(this.url,{params:queryParams});
  }

  bookTheFlight(user: UserDTO){
        return this.http.post(this.url, user);
  } 

  cancelTheFlight(PNR:string, PhoneNumber:string){
    let queryParams = new HttpParams();
    queryParams = queryParams.append("PhoneNumber", PhoneNumber).append("PNR",PNR);
    return this.http.put(this.url,queryParams);
  }

  findTheTicket(PNR:string, PhoneNumber:string){
    let newUrl:string = this.url+"/getBookingDetails";
    let queryParams = new HttpParams();
    queryParams = queryParams.append("PhoneNumber", PhoneNumber).append("PNR",PNR);
    return this.http.get<Ticket>(newUrl,{params:queryParams});
  }

  getHistory(PhoneNumber:string){
    localStorage.getItem("HOST");
    let newUrl:string = this.url+"/history";
    let queryParams = new HttpParams();
    queryParams = queryParams.append("PhoneNumber", PhoneNumber);
    console.log(this.url);
    return this.http.get(newUrl,{params:queryParams});
  }
}
