import { passanger } from "./UserDTO";

export interface Ticket{
    pnr:string;
    bookingId:number;
	bookingDate:string;
    source:string;
	destination:string;
	flightNo:string;
	startDate:string;
	startTime:string;
	boardingTime:string;
	passanger:passanger[];
}

export const passangerArray:Ticket[] =[{
    pnr:'abcxyz',
    bookingId:23,
	bookingDate:'01-01-2022',
    source:'delhi',
	destination:'mumbai',
	flightNo:'20',
	startDate:'03-03-2022',
	startTime:'11:50',
	boardingTime:'10:50',
	passanger:
        [
        {
            firstname:'anil',
            lastName:'tailor',
            dateOfBirth:'18-05-1995',
            seatNumber:25,
            seatType:'Business',
        },
        {
            firstname:'anil',
            lastName:'tailor',
            dateOfBirth:'18-05-1995',
            seatNumber:25,
            seatType:'Business',
        },
        {
            firstname:'anil',
            lastName:'tailor',
            dateOfBirth:'18-05-1995',
            seatNumber:25,
            seatType:'Business',
        }

        ],
},
{
    pnr:'abcxyz',
    bookingId:23,
	bookingDate:'01-01-2022',
    source:'delhi',
	destination:'mumbai',
	flightNo:'20',
	startDate:'03-03-2022',
	startTime:'11:50',
	boardingTime:'10:50',
	passanger:
        [
        {
            firstname:'anil',
            lastName:'tailor',
            dateOfBirth:'18-05-1995',
            seatNumber:25,
            seatType:'Business',
        },
        {
            firstname:'anil',
            lastName:'tailor',
            dateOfBirth:'18-05-1995',
            seatNumber:25,
            seatType:'Business',
        },
        {
            firstname:'anil',
            lastName:'tailor',
            dateOfBirth:'18-05-1995',
            seatNumber:25,
            seatType:'Business',
        }
        ],

}
]