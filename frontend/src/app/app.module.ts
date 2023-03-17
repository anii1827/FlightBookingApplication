import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from "@angular/material/toolbar";
import { SideNavbarComponent } from './user/side-navbar/side-navbar.component'
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatMenuModule} from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';
import { MatStepperModule } from '@angular/material/stepper';
import { BookingComponent } from './user/booking/booking.component';
import { MatInputModule } from '@angular/material/input';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { FligthbookingsearchComponent } from './user/fligthbookingsearch/fligthbookingsearch.component';
import { MatAutocompleteModule} from '@angular/material/autocomplete';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatMomentDateModule } from '@angular/material-moment-adapter'
import { MatNativeDateModule } from '@angular/material/core';
import { MatTableModule } from '@angular/material/table';
import { MatSelectModule } from '@angular/material/select';
import { HttpClientModule } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { FlightCancelationComponent } from './user/flight-cancelation/flight-cancelation.component';
import { MatDialogModule } from '@angular/material/dialog';
import { TicketSearchComponent } from './user/ticket-search/ticket-search.component';
import { HistoryComponent } from './user/history/history.component';
import { LoginComponent } from './login/login.component'
import { MatFormFieldModule } from '@angular/material/form-field';
import { HomeComponent } from './admin/home/home.component';
import { AdminHeaderComponent } from './admin/admin-header/admin-header.component';
import { FlightRegisterComponent } from './admin/flight-register/flight-register.component';
import { FlightBlockComponent } from './admin/flight-block/flight-block.component';
import { FlightSechduleComponent } from './admin/flight-sechdule/flight-sechdule.component';
import { StartPageComponent } from './admin/start-page/start-page.component';
import {MatExpansionModule} from '@angular/material/expansion';
import { NgxMatDateFormats, NgxMatDatetimePickerModule, NgxMatNativeDateModule,
  NgxMatTimepickerModule} from '@angular-material-components/datetime-picker';
import { StartpageComponent } from './user/startpage/startpage.component';
import { AllFlightsComponent } from './admin/all-flights/all-flights.component';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';


FormsModule
@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    SideNavbarComponent,
    BookingComponent,
    FligthbookingsearchComponent,
    FlightCancelationComponent,
    TicketSearchComponent,
    HistoryComponent,
    LoginComponent,
    HomeComponent,
    AdminHeaderComponent,
    FlightRegisterComponent,
    FlightBlockComponent,
    FlightSechduleComponent,
    StartPageComponent,
    StartpageComponent,
    AllFlightsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,  
    MatToolbarModule, 
    MatSidenavModule,
    MatMenuModule,
    MatIconModule,
    MatStepperModule,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatAutocompleteModule,
    MatDatepickerModule,
    MatMomentDateModule,
    MatNativeDateModule,
    MatTableModule,
    MatSelectModule,
    HttpClientModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatExpansionModule,
    NgxMatDatetimePickerModule,
    NgxMatNativeDateModule,
    NgxMatTimepickerModule,
    MatProgressSpinnerModule,
  ],
  entryComponents:[
  
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
