package com.tpe;

import java.time.LocalDateTime;



import com.tpe.enums.ReservationStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationDTO {

	private Long id;
	
	private Long carId;
	
	
	private LocalDateTime pickUpTime;
	
	
	private LocalDateTime dropOffTime;
	
	
	private String pickUpLocation;
	
	
	private String dropOffLocation;
	
	
	private ReservationStatus  status;
	
	
	private Double totalPrice;
	
}
