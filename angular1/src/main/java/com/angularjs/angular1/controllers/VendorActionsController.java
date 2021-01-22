package com.angularjs.angular1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.angularjs.angular1.bean.FeedbackBean;
import com.angularjs.angular1.bean.VehicleBean;
import com.angularjs.angular1.dao.BookingDao;
import com.angularjs.angular1.dao.VehicleDao;
import com.angularjs.angular1.entity.VehicleEntity;
import com.angularjs.angular1.services.VendorActionsService;

@RestController
public class VendorActionsController {

	@Autowired
	VendorActionsService vendoractionsservice;

	@Autowired
	VehicleDao vehicledao;

	@Autowired
	BookingDao bookingrepo;

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/getvendorvehicles/{vendorid}")
	ResponseEntity<List<VehicleEntity>> getVendorVehicles(@PathVariable("vendorid") String vendorid) {

		if (vehicledao.findByVendorid(Long.parseLong(vendorid)) != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(vehicledao.findByVendorid(Long.parseLong(vendorid)));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(vehicledao.findByVendorid(Long.parseLong(vendorid)));
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/addvehicle")
	ResponseEntity<Object> addVehicle(@RequestBody VehicleBean vehicle) {
		System.out.println(vehicle.bookingstatus + vehicle.vehicleid + vehicle.vehiclename + vehicle.vehiclenumber);
		if (vehicledao.findByVehiclenumber(vehicle.vehiclenumber) == null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(vendoractionsservice.addVehicleService(vehicle));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Vehicle Already Exists with that Vehicle Number!");
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/viewbookingsforvendor/{vendorid}")
	ResponseEntity<Object> viewBookings(@PathVariable("vendorid") long vendorid) {
		if (bookingrepo.findByVendorid(vendorid) != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(bookingrepo.findByVendorid(vendorid));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Bookings Yet!");
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/deletevehiclebyvendor")
	ResponseEntity<Object> deleteVehicle(@RequestBody VehicleBean vehicle) {
		if (vehicledao.findById(vehicle.vehicleid).isPresent()) {
			vendoractionsservice.deleteVehicleService(vehicle);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(vehicle);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle already deleted or does not exist!");
		}
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/givefeedbackbyvendor")
	ResponseEntity<Object> giveFeedback(@RequestBody FeedbackBean feedback){
		try {
			return ResponseEntity
					.status(HttpStatus.ACCEPTED)
					.body(vendoractionsservice.giveFeedbackService(feedback));
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.BAD_GATEWAY)
					.body("Internal Server Error!");
		}
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/getfeedbackbyvendor/{userid}")
	ResponseEntity<Object> getFeedback(@PathVariable("userid") int userid){
		if(!vendoractionsservice.viewFeedbackService(userid).isEmpty()) {
			return ResponseEntity
					.status(HttpStatus.ACCEPTED)
					.body(vendoractionsservice.viewFeedbackService(userid));
		}else {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("No feedbacks yet!");
		}
	}
	
	
}
