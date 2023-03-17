export class UserDTO{
    name!:String;
    email!:String;
    phoneNumber!:String ;
    flightId!:number;
    passangerDetails!:passanger[];

    // setName(name:String){
    //     this.name = name;
    // }
}
export class passanger{
    firstname!:String;
	lastName!:String;
	dateOfBirth!:String;
	seatNumber!:number;
	seatType!:String;
}