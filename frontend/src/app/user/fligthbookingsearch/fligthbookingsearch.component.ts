import { animate, state, style, transition, trigger } from '@angular/animations';
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import { State, states } from '../../../model/Stats';
import { Flight } from '../../../model/FlightDetails';
import { FlightSharedServiceService } from '../../service/flight-shared-service.service';
import { Router } from '@angular/router';
import { FlightServiceService } from '../../service/flight-service.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-fligthbookingsearch',
  templateUrl: './fligthbookingsearch.component.html',
  styleUrls: ['./fligthbookingsearch.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})

export class FligthbookingsearchComponent implements OnInit {
  searchsucess:boolean = false;
  searchFormGroup!: FormGroup;
  service: FlightSharedServiceService;
  stateCtrl = new FormControl();
  states : State[]=states;
  filteredStates: Observable<State[]>;
  dataSource!:Flight[];
  selectedFlight!:Flight;
  columnsToDisplay = ['flightId', 'source', 'destination', 'totalTime']
  expandcolumnsToDisplay = ['flightId', 'source', 'destination', 'startTime','flightNo',
  'airline',
  'availableSeatInBusinessClass',
  'availableSeatInNonBusinessClass',
  'endTime',
  'available',
  'totalTime',
  'startTimeinHoursandMinute',
  'endTimeinHoursandMinute',
  'ticketPriceForBusinessClass',
  'ticketPriceForNonBusinessClass'
];
  expandedElement: Flight | null;

  constructor(private flightSharedServie:FlightSharedServiceService, private changeDetectorRefs: ChangeDetectorRef, private datePipe:DatePipe, private _formBuilder:FormBuilder, private flightservice:FlightServiceService, SharedServiceService: FlightSharedServiceService, private router: Router) {
    this.service = SharedServiceService;
    this.filteredStates = this.stateCtrl.valueChanges.pipe(
      startWith(''),
      map((state: string) => (state ? this._filterStates(state) : this.states.slice())),
    );
    this.expandedElement=null;
    
  }

  private _filterStates(value: string): State[] {
    const filterValue = value.toLowerCase();
    return this.states.filter(state => state.name.toLowerCase().includes(filterValue));
  }
  ngOnInit(): void {
   this.searchFormGroup = this._formBuilder.group({
        Source: ['', Validators.required],
        Destination: ['', Validators.required],
        Date: ['', Validators.required],
    })
  }

  searchFlight(){
    this.searchsucess=true;
    let source = this.searchFormGroup.controls['Source'].value;
    let destination = this.searchFormGroup.controls['Destination'].value;
    let date = this.datePipe.transform(this.searchFormGroup.controls['Date'].value, 'yyyy-MM-dd');
    
    this.flightservice.fetchFlight(source,destination,date).subscribe(
      (data: Flight[])=>{
      this.searchsucess=false;
      this.dataSource = data;
    },
    (error)=>{
      this.searchsucess=false;
      console.log(error);
      alert(error['error']['message']);
    })
  }

  bookflight(id: number){
    let flight: Flight = <Flight>this.dataSource.find((data)=>{
     return data.flightId==id;
    })
    this.flightSharedServie.setFlight(flight);
    this.router.navigate(['/book']);
  } 
}
