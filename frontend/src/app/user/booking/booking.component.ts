import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators, FormArray, Form } from '@angular/forms';
import { FlightSharedServiceService } from '../../service/flight-shared-service.service';
import { Flight } from '../../../model/FlightDetails'
import { map } from 'rxjs';
import { passanger, UserDTO } from 'src/model/UserDTO';
import { FlightServiceService } from '../../service/flight-service.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent implements OnInit {
  totalPayment:number=0;
  isLinear = true;
  userGroup!: FormGroup;
  selectedValue: any= new FormControl(1);
  passangerArray= new FormArray([]);
  passanger!: FormGroup;
  Id!:number;
  seatNumber: number[]=[1,2,3];
  flight: Flight;  
  isEditable = false;
  TotalAmount: Map<number,number>;
  User:UserDTO = new UserDTO();
  reserveSeat: Set<number>;
  bookingSucessMessage!: String;
  bookingSuccess:boolean = false;

  constructor(private router: Router, private flightService:FlightServiceService, private _formBuilder: FormBuilder, private FService: FlightSharedServiceService) { 
    this.flight = this.FService.getFlight();
    console.log(this.flight);
    this.TotalAmount=new Map();
    this.reserveSeat = new Set<number>();
  }
  

  public working(): void{
      // console.log(this.selectedValue.value);
      this.clearFormArray(this.getpassangerArray());
          for(let i=0;i<this.selectedValue.value;i++){
              this.addpassanger();
          }

          for(let i=0;i<this.getpassangerArray().length;i++){  
            // console.log(this.getpassangerArray().controls[i].get('PassangerSeatType'));
            this.getpassangerArray().controls[i].get('PassangerSeatType')?.valueChanges.subscribe(data=>{
                    if(data=="Business"){
                      this.TotalAmount.set(i,this.flight.ticketPriceForBusinessClass);
                      this.getpassangerArray().controls[i].get('PassangerSeatOption')?.setValue(this.flight.availableSeatNumber['Business']);
                    }
                    else{
                      this.TotalAmount.set(i,this.flight.ticketPriceForNonBusinessClass);
                        this.getpassangerArray().controls[i].get('PassangerSeatOption')?.setValue(this.flight.availableSeatNumber['NonBusiness']);
                    }
                    this.getpassangerArray().controls[i].get('PassangerSeatNumber')?.setValue('');
            })

            this.getpassangerArray().controls[i].get('PassangerSeatNumber')?.valueChanges.subscribe(data=>{
              if(data){
                console.log(data);
                if(this.reserveSeat.has(data)){
                   alert("this seat already selected by you for other passanger! choose another seat.");
                }
                else{
                  this.reserveSeat.add(data);
                }
              }
          })
        }
        
  }

  //data is rendering
  check(): void{
    this.TotalAmount.forEach((value, key)=>{
        this.totalPayment+=value;
    });
    // console.log(this.passanger.get('pass') as FormArray);
  }
  

  ngOnInit(): void {    
    this.userGroup = this._formBuilder.group({
      Name: ['', Validators.required],
      Email: ['', Validators.required],
      PhoneNumber: ['', Validators.required],
    });

    this.passanger = this._formBuilder.group({
      pass:this._formBuilder.array([])
    });
  }

  //passanger array object
  getpassangerArray(): FormArray{
    return this.passanger.get('pass') as FormArray;
  }

  //get the new form group
  newPassnager(): FormGroup{
    return this._formBuilder.group({
        PassangerFName: ['', Validators.required],
        PassangerLName: ['', Validators.required],
        PassangerDOB: ['', Validators.required],
        PassangerSeatType: ['', Validators.required],
        PassangerSeatNumber: ['', Validators.required],
        PassangerSeatOption: [[]]
      });
    }

    

    //adding new passanger
    addpassanger(){
      this.getpassangerArray().push(this.newPassnager());
    }

    //removing all the passanger before adding the passanger
    clearFormArray = (formArray: FormArray) => {
      while (formArray.length !== 0) {
        formArray.removeAt(0)
      }
    }


    booktheTicket(){
            this.bookingSuccess=true;
            this.createUser();
    }

    createUser(){
          this.User.name = this.userGroup.controls['Name'].value;
          this.User.email=this.userGroup.controls['Email'].value;
          this.User.phoneNumber=this.userGroup.controls['PhoneNumber'].value;
          this.User.flightId=+this.flight.flightId;
          let pass: passanger[] = new Array;
          let form: FormArray = this.passanger.get('pass') as FormArray;
          form.value.forEach((element: any) => {
                let p:passanger = new passanger();
                p.firstname = element['PassangerFName'];
                p.lastName = element['PassangerLName'];
                p.dateOfBirth = element['PassangerDOB'];
                p.seatType = element['PassangerSeatType'];
                p.seatNumber = element['PassangerSeatNumber'];
                pass.push(p);
                // console.log(element);
          });
          this.User.passangerDetails=pass;
          console.log(this.User);
          this.flightService.bookTheFlight(this.User).subscribe(
          (data)=>{
                console.log("success Response");
                this.bookingSucessMessage = JSON.parse(JSON.stringify(data))['message'];
                this.bookingSuccess=false;
                alert(this.bookingSucessMessage);
                this.router.navigate(['/bookTicket']);
          },
          (error)=>{
            this.bookingSuccess=false;
            console.log(error);
            alert(error['error']['message']);
          });
          
    }


}





