package com.angularjs.angular1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.angularjs.angular1.bean.FeedbackBean;
import com.angularjs.angular1.bean.VehicleBean;
import com.angularjs.angular1.dao.BookingDao;
import com.angularjs.angular1.dao.FeedbackDao;
import com.angularjs.angular1.dao.VehicleDao;
import com.angularjs.angular1.entity.BookingEntity;
import com.angularjs.angular1.entity.FeedbackEntity;
import com.angularjs.angular1.entity.VehicleEntity;

@Component
public class VendorActionsService {

	@Autowired
	VehicleDao vehiclerepo;
	
	@Autowired
	BookingDao bookingrepo;
	
	@Autowired
	FeedbackDao feedbackrepo;
	
	@Autowired
	BookingsAndVehicleRefreshService refreshservice;

	VehicleEntity vehicle;

	public VehicleEntity addVehicleService(VehicleBean vehicle) {

		this.vehicle = new VehicleEntity();
		this.vehicle.setVehiclenumber(vehicle.vehiclenumber);
		this.vehicle.setVehiclename(vehicle.vehiclename);
		this.vehicle.setVendorid(vehicle.vendorid);
		this.vehicle.setPrice(vehicle.price);
		this.vehicle.setBookingstatus(vehicle.bookingstatus);

		return vehiclerepo.save(this.vehicle);
	}

	public void deleteVehicleService(VehicleBean vehicle) {

		List<BookingEntity> bookings = bookingrepo.findByVehicleid(vehicle.vehicleid);
		refreshservice.refreshBookingStatuses();
		for(BookingEntity booking:bookings) {
			bookingrepo.updateBookingById("Vehicle Removed by the owner, sorry for the inconvinience!", 0, 0, booking.getBookingid());
		}
		vehiclerepo.deleteById(vehicle.vehicleid);
	}
	
	public FeedbackEntity giveFeedbackService(FeedbackBean feedbackbean) {
		FeedbackEntity feedbackentity = new FeedbackEntity();
		feedbackentity.setFeedback(feedbackbean.feedback);
		feedbackentity.setRating(feedbackbean.rating);
		feedbackentity.setUserid(feedbackbean.userid);
		feedbackentity.setVehicleid(feedbackbean.vehicleid);
		return feedbackrepo.save(feedbackentity);
	}
	
	public List<FeedbackEntity> viewFeedbackService(int userid){
		return feedbackrepo.findByUserid(userid);
	}
	
}
