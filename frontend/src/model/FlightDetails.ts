// import { map } from "rxjs/operators"
export interface Flight {
  flightId: Number;
  flightNo: String ;
  source: String ;
  destination: String ;
  airLine: String;
  availableSeatInBusinessClass:number;
  availableSeatInNonBusinessClass:number;
  startTime: String;
  endTime: String;
  available: boolean;
  totalTime: String
  startTimeinHoursandMinute: String;
  endTimeinHoursandMinute:String;
  ticketPriceForBusinessClass:number;
  ticketPriceForNonBusinessClass:number;
  availableSeatNumber:SeatDetails; 
}

interface SeatDetails{
      Business:number[];
      NonBusiness:number[];
}
 

// export const FlightData: Flight[] = [
//   {
//     flightId: 15,
//     flightNo: 'Air-200',
//     source: 'BLR',
//     destination: 'Udaipur',
//     airLine: 'AIRINDIA',
//     availableSeatInBusinessClass:10,
//     availableSeatInNonBusinessClass:10,
//     startTime: 'Jan 15 2021 15:20',
//     endTime: 'Jan 16 2021 15:20',
//     available: true,
//     totalTime: '24',
//     startTimeinHoursandMinute: '15:20',
//     endTimeinHoursandMinute:'15:20',
//     ticketPriceForBusinessClass:1520.00,
//     ticketPriceForNonBusinessClass:1000,
//     availableSeatNumber:new Map<string, Array<Number>>([
//       ["Business", [1,2,3]],
//       ["NonBusiness", [4,5,6]]	
//     ])
//   },
//   // {
//   //   flightId: 16,
//   //   flightNo: 'Air-200',
//   //   source: 'BLR',
//   //   destination: 'Udaipur',
//   //   airLine: 'AIRINDIA',
//   //   availableSeatInBusinessClass:10,
//   //   availableSeatInNonBusinessClass:10,
//   //   startTime: 'Jan 15 2021 15:20',
//   //   endTime: 'Jan 16 2021 15:20',
//   //   available: true,
//   //   totalTime: '24',
//   //   startTimeinHoursandMinute: '15:20',
//   //   endTimeinHoursandMinute:'15:20',
//   //   ticketPriceForBusinessClass:1520.00,
//   //   ticketPriceForNonBusinessClass:1000,
//   //   availableSeatNumber:new Map<string, Array<Number>>([
//   //     ["Business", [10,11,13]],
//   //     ["NonBusiness", [14,15,16]]	
//   //   ])
//   // }
//   ]

  
  