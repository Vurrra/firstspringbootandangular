package com.angularjs.angular1.controllers;

import java.time.ZoneId;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.angularjs.angular1.bean.BookingBean;
import com.angularjs.angular1.bean.FeedbackBean;
import com.angularjs.angular1.dao.BookingDao;
import com.angularjs.angular1.dao.VehicleDao;
import com.angularjs.angular1.entity.BookingEntity;
import com.angularjs.angular1.services.BookingsAndVehicleRefreshService;
import com.angularjs.angular1.services.CustomerActionsService;

@RestController
public class CustomerActionsController {

	@Autowired
	CustomerActionsService customeractionsservice;

	@Autowired
	BookingsAndVehicleRefreshService bookingsandvehiclerefreshservice;

	@Autowired
	VehicleDao vehiclerepo;

	@Autowired
	BookingDao bookingrepo;

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/getcarsforcustomer")
	ResponseEntity<Object> getCars() {
		bookingsandvehiclerefreshservice.refreshBookingStatuses();
		if (customeractionsservice.getCarsForCustomer() != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(customeractionsservice.getCarsForCustomer());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Cars on the website yet!");
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/getbookingsforselectedcar/{vehicleid}")
	ResponseEntity<Object> getBookingsForSelectedCar(@PathVariable("vehicleid") long vehicleid) {
		bookingsandvehiclerefreshservice.refreshBookingStatuses();
		if (customeractionsservice.getBookingsForSelectedCar(vehicleid) != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(customeractionsservice.getBookingsForSelectedCar(vehicleid));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Bookings for the Car yet!");
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/bookcar/{vehicleid}")
	ResponseEntity<Object> bookCar(@PathVariable("vehicleid") long vehicleid, @RequestBody BookingBean booking) {
		bookingsandvehiclerefreshservice.refreshBookingStatuses();
		ZoneId zone=ZoneId.of("Asia/Kolkata");
		booking.bookingstarttime=booking.bookingstarttime.withZoneSameInstant(zone);
		booking.bookingendtime=booking.bookingendtime.withZoneSameInstant(zone);
		List<BookingEntity> bookings = bookingrepo.findByVehicleid(vehicleid);
		try {
			if (bookings.isEmpty()) {
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(customeractionsservice.bookCar(booking));
			} else {
				for (BookingEntity existingbooking : bookings) {
					if (!evalDatesOf(booking,existingbooking)) {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
								"Booking times overlapping with other bookings! \n refer to booked during column!");
					}
				}
				System.out.println("no overlapping so booking");
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(customeractionsservice.bookCar(booking));
			}
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot find car, Try again later!");
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/getbookingsforcustomer/{userid}")
	ResponseEntity<Object> getBookingsForCustomer(@PathVariable("userid") int userid) {
		if (customeractionsservice.getBookingsForCustomer(userid) != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(customeractionsservice.getBookingsForCustomer(userid));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Bookings Done Yet!");
		}
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/cancelbookingbycustomer")
	ResponseEntity<Object> cancelBooking(@RequestBody BookingBean booking) {
		if(bookingrepo.findById(booking.bookingid).isPresent()) {
			customeractionsservice.cancelBookingService(booking.bookingid);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(booking);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking Not Found!");
		}
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/givefeedbackbycustomer")
	ResponseEntity<Object> giveFeedback(@RequestBody FeedbackBean feedback){
		try {
			return ResponseEntity
					.status(HttpStatus.ACCEPTED)
					.body(customeractionsservice.giveFeedbackService(feedback));
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.BAD_GATEWAY)
					.body("Internal Server Error!");
		}
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/getfeedbackbycustomer/{userid}")
	ResponseEntity<Object> getFeedback(@PathVariable("userid") int userid){
		if(!customeractionsservice.viewFeedbackService(userid).isEmpty()) {
			return ResponseEntity
					.status(HttpStatus.ACCEPTED)
					.body(customeractionsservice.viewFeedbackService(userid));
		}else {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("No feedbacks yet!");
		}
	}
	
	boolean evalDatesOf(BookingBean booking, BookingEntity existingbooking) {
		boolean flag=true;
		if(booking.bookingstarttime.isAfter(existingbooking.getBookingstarttime())) {
			if(booking.bookingstarttime.isBefore(existingbooking.getBookingendtime())) 
			{
				flag=false; 
				return false;
			}else {
				flag=true;
			}
		}else if(booking.bookingstarttime.isBefore(existingbooking.getBookingstarttime())) {
				flag=true;
		}else {
			flag=false;
			return flag;
		}
		if(booking.bookingendtime.isAfter(existingbooking.getBookingstarttime()) ){
			if(booking.bookingendtime.isBefore(existingbooking.getBookingendtime())) {
				flag=false;
				return false;
			}else {
				flag=true;
			}
		}else if(booking.bookingendtime.isBefore(existingbooking.getBookingstarttime())) {
			
			flag=true;
	    }else {
		    flag=false;
	    }
			return flag;
		
	}

}
