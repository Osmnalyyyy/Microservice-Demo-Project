package com.tpe.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.tpe.CarDTO;
import com.tpe.ReservationDTO;
import com.tpe.controller.request.ReservationRequest;
import com.tpe.domain.Reservation;
import com.tpe.enums.ReservationStatus;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.ReservationRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReservationService {

		private ReservationRepository reservationRepository;
	
		private EurekaClient eurekaClient;
	
		private RestTemplate restTemplate;
		
		private ModelMapper modelMapper;
		
		public void saveReservation(Long carId,ReservationRequest reservationRequest) {
		InstanceInfo instanceInfo=eurekaClient.getApplication("car-service").getInstances().get(0);
		String baseUrl=instanceInfo.getHomePageUrl();
		
		String path="/car/";
		//localhost:8084/car/1
		String servicePath=baseUrl+path+carId.toString();
		
		ResponseEntity<CarDTO> carDTOresponse=restTemplate.getForEntity(servicePath, CarDTO.class);
	   
		if(!(carDTOresponse.getStatusCode()==HttpStatus.OK)) {
			throw new ResourceNotFoundException("Car not found with id:${carId}");
		}
		
		CarDTO carDTO=carDTOresponse.getBody();
		Reservation reservation=new Reservation();
		
		reservation.setCarId(carDTOresponse.getBody().getId());
		
		reservation.setPickUpTime(reservationRequest.getPickUpTime());
		reservation.setDropOffTime(reservationRequest.getDropOffTime());
		reservation.setPickUpLocation(reservationRequest.getPickUpLocation());
		reservation.setDropOffLocation(reservationRequest.getDropOffLocation());
		reservation.setStatus(ReservationStatus.CREATED);
		
		double tp=totalPrice(reservationRequest.getPickUpTime(), reservationRequest.getDropOffTime(), carDTO);
		reservation.setTotalPrice(tp);
		
		reservationRepository.save(reservation);
		
		}
		
		public List<ReservationDTO> getAllREservation(){
			List<Reservation> rList=reservationRepository.findAll();
			List<ReservationDTO> reservationDTOs=rList.stream().map(this::mapReservationToDTO).collect(Collectors.toList());
		
			return reservationDTOs;
		}
		
		private ReservationDTO mapReservationToDTO(Reservation reservation) {
			ReservationDTO reservationDTO=modelMapper.map(reservation, ReservationDTO.class);
			
			return reservationDTO;
		}
		
		
		
		
		
		
		
		
		
		private Double totalPrice(LocalDateTime PickUpTime, LocalDateTime DropOffTime ,CarDTO carDTO) {
		Long hours=(new Reservation()).getTotalHours( PickUpTime,  DropOffTime);
		return hours*carDTO.getPricePerHour();
		}
		
}
