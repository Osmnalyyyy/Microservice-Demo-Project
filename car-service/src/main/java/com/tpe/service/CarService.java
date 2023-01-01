package com.tpe.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tpe.CarDTO;
import com.tpe.controller.request.CarRequest;
import com.tpe.domain.Car;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.CarRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarService {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private CarRepository carRepository;
	
	public void saveCar(CarRequest carRequest) {
		
//	Car car=modelMapper.map(carRequest, Car.class);	
		
	Car car=new Car();
	
	car.setAge(carRequest.getAge());
	car.setBrand(carRequest.getBrand());
	car.setDoors(carRequest.getDoors());
	car.setModel(carRequest.getModel());
	car.setPricePerHour(carRequest.getPricePerHour());
	
	carRepository.save(car);
	
	}
	
	
	
	public List<CarDTO> getAllCars(){
	List<Car> carList= carRepository.findAll();
	List<CarDTO> carDTOs=carList.stream().map(this::mapCarToCarDTO).collect(Collectors.toList());
	return carDTOs;
	}
	
	
	private CarDTO mapCarToCarDTO(Car car) {
		CarDTO carDTO=modelMapper.map(car, CarDTO.class);
		return carDTO;
	}
	
	
	public CarDTO getById(Long id) {
	Car car=carRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Car not found with id ${id}"));
	CarDTO carDTO=modelMapper.map(car, CarDTO.class);
	return carDTO;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
