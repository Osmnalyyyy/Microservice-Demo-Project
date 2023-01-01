package com.tpe.controller.request;

import java.time.LocalDateTime;


import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ReservationRequest {

	
	
	
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="MM/dd/yyyy HH:mm:ss",timezone="Turkey")
	private LocalDateTime pickUpTime;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="MM/dd/yyyy HH:mm:ss",timezone="Turkey")
	private LocalDateTime dropOffTime;
	

	private String pickUpLocation;
	
	
	private String dropOffLocation;
	

	
	
	
}
