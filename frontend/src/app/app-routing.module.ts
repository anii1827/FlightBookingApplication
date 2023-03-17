import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AllFlightsComponent } from './admin/all-flights/all-flights.component';
import { FlightBlockComponent } from './admin/flight-block/flight-block.component';
import { FlightRegisterComponent } from './admin/flight-register/flight-register.component';
import { FlightSechduleComponent } from './admin/flight-sechdule/flight-sechdule.component';
import { HomeComponent } from './admin/home/home.component';
import { StartPageComponent } from './admin/start-page/start-page.component';
import { AuthGuard } from './guard/auth.guard';
import { BookingComponent } from './user/booking/booking.component';
import { FlightCancelationComponent } from './user/flight-cancelation/flight-cancelation.component';
import { FligthbookingsearchComponent } from './user/fligthbookingsearch/fligthbookingsearch.component';
import { HistoryComponent } from './user/history/history.component';
import { SideNavbarComponent } from './user/side-navbar/side-navbar.component';
import { StartpageComponent } from './user/startpage/startpage.component';
import { TicketSearchComponent } from './user/ticket-search/ticket-search.component';
const routes: Routes = [
  {
    path: '',
    component: SideNavbarComponent,
    children:[
      {
        path: '',
        component: StartpageComponent
      },
      {
        path: 'bookTicket', 
        component: FligthbookingsearchComponent
      },
      {
        path: 'book', 
        component: BookingComponent
      },
      {
        path: 'cancelTicket',
        component: FlightCancelationComponent
      },
      {
        path: 'ticketSearch',
        component: TicketSearchComponent
      },
      {
        path: 'history',
        component: HistoryComponent
      },
    ]
  },
  {
    path: 'admin',
    component: HomeComponent,
    canActivate: [AuthGuard],
    children:[
        {
          path: '',
          component: StartPageComponent
        },
        {
          path: 'register',
          component: FlightRegisterComponent
        },
        {
          path: 'block',
          component: FlightBlockComponent
        },
        {
          path: 'block/:id',
          component: FlightBlockComponent
        },
        {
          path: 'sechdule',
          component: FlightSechduleComponent,  
        },
        {
          path: 'sechdule/:id',
          component: FlightSechduleComponent,  
        },
        {
          path: 'flights',
          component: AllFlightsComponent
        }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }


