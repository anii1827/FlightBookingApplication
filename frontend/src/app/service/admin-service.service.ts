import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Flight } from '../admin/flightDto';

@Injectable({
  providedIn: 'root'
})
export class AdminServiceService {
  baseUrl: string = "http://" + localStorage.getItem('HOST') + ":8989/admin/v1.0/api"

  constructor(private http: HttpClient) { }

  getToken(name: string, pass: string) {
    let url = this.baseUrl + "/authenticate";
    let cred: loginCred = {
      username: name,
      password: pass,
    }
    return this.http.post(url, cred);
  }

  addFlight(flight: Flight) {
    let url = this.baseUrl + "/inventory/add";
    var head = this.getHeader();
    //  console.log(head.get('Authorization'));
    return this.http.post(url, flight, { headers: head });
  }

  private getHeader(): HttpHeaders {
    let token = "Bearer " + localStorage.getItem('token')
    const headers = new HttpHeaders({ "Authorization": token });
    console.log(headers.get('Authorization'));
    return headers;
  }

  sechduleTheflight(id: number, startttime: any, endtime: any) {
    let url = this.baseUrl + "/inventory/Schedule";
    var header = this.getHeader();
    let query = new HttpParams();
    query = query.append("flightId", id).append("starttime", startttime).append("endtime", endtime);
    console.log(header);
    console.log(query);
    return this.http.put(url, null, {
      headers: header,
      params: query,
    });
  }

  getall() {
    var header = this.getHeader();
    return this.http.get(this.baseUrl, { headers: header });
  }

  blockflight(flightId: number) {
    let query = new HttpParams();
    query = query.append("flightId", flightId);
    var header = this.getHeader();
    let url = this.baseUrl + "/inventory/block";
    return this.http.put(url, null, {
      headers: header,
      params: query
    });
  }
}

interface loginCred {
  username: string;
  password: string;
}


