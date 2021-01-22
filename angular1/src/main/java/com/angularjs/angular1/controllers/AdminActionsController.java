package com.angularjs.angular1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.angularjs.angular1.services.AdminActionsService;

@RestController
public class AdminActionsController {
	
	@Autowired
	AdminActionsService adminactionsservice;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/getvehiclesforadmin")
	ResponseEntity<Object> getVehicles(){
		if(adminactionsservice.getAllVehicles()!=null) {
			return ResponseEntity
					.status(HttpStatus.ACCEPTED)
					.body(adminactionsservice.getAllVehicles());
		}else {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body("No Vehicles on the website yet! Work on marketing!");
		}
		
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/getusersforadmin")
	ResponseEntity<Object> getUsers(){
		if(adminactionsservice.getAllUsers()!=null) {
			return ResponseEntity
					.status(HttpStatus.ACCEPTED)
					.body(adminactionsservice.getAllUsers());
		}else {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body("No Users on the website yet! Work on marketing!");
		}
		
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/getbookingsforadmin")
	ResponseEntity<Object> getBookings(){
		if(adminactionsservice.getAllBookings()!=null) {
			return ResponseEntity
					.status(HttpStatus.ACCEPTED)
					.body(adminactionsservice.getAllBookings());
		}else {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body("No Users on the website yet! Work on marketing!");
		}
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/getfeedbacksforadmin")
	ResponseEntity<Object> getFeedbacks(){
		if(!adminactionsservice.getAllFeedbacks().isEmpty()) {
			return ResponseEntity
					.status(HttpStatus.ACCEPTED)
					.body(adminactionsservice.getAllFeedbacks());
		}else {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("No Feedbacks Yet!");
		}
	}
	
}
