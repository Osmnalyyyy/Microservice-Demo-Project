package com.tpe.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tpe.CarDTO;
import com.tpe.controller.request.CarRequest;
import com.tpe.service.CarService;

@RestController
@RequestMapping("/car")
public class CarController {
	@Autowired
	private CarService carService;
	

	@PostMapping
	public ResponseEntity<Map<String,String>> saveCar(@RequestBody CarRequest carRequest){
		carService.saveCar(carRequest);
		Map<String,String> map=new HashMap<>();
		map.put("message", "Car successfully saved");
		map.put("success", "true");
		
		return new ResponseEntity<>(map,HttpStatus.CREATED);
	}
	
	
	@GetMapping
	public ResponseEntity<List<CarDTO>> getAllCars(){
		List<CarDTO> allCars=carService.getAllCars();
		
		return ResponseEntity.ok(allCars);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CarDTO> getCar(@PathVariable Long id){
	CarDTO carDTO=carService.getById(id);
	return ResponseEntity.ok(carDTO);
	}
	
	
	
	
	
	
}
