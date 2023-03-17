export interface Flight{
	 flightNo:string;
	 source:string;
	 destination:string;
	 airLine:string;
	 totalSeatInBusinessClass:number;
	 totalSeatInNonBusinessClass:number;
	 ticketPriceForBusinessClass:number;
	 ticketPriceForNonBusinessClass:number;
}
export interface receiveFlight extends Flight{
	inventory_Id:number;
	flightId:number;
	airLine:string;
	availableSeatInBusinessClass:number,
	availableSeatInNonBusinessClass:number,
	startTime:string,
	endTime:string,
	available:string,
	totalTime:string,
	sechduled:Boolean,
	registerDate:string;
}
