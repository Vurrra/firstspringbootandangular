package com.angularjs.angular1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.angularjs.angular1.bean.BookingBean;
import com.angularjs.angular1.bean.FeedbackBean;
import com.angularjs.angular1.dao.BookingDao;
import com.angularjs.angular1.dao.FeedbackDao;
import com.angularjs.angular1.dao.VehicleDao;
import com.angularjs.angular1.entity.BookingEntity;
import com.angularjs.angular1.entity.FeedbackEntity;
import com.angularjs.angular1.entity.VehicleEntity;

@Component
public class CustomerActionsService {

	@Autowired
	VehicleDao vehiclerepo;

	@Autowired
	BookingDao bookingrepo;
	
	@Autowired
	FeedbackDao feedbackrepo;
	

	public List<VehicleEntity> getCarsForCustomer() {
		return vehiclerepo.findAll();
	}

	public BookingEntity bookCar(BookingBean bookingbean) {
		BookingEntity booking = new BookingEntity();
		booking.setBookingduration(bookingbean.bookingduration);
		booking.setBookingendtime(bookingbean.bookingendtime);
		booking.setBookingstarttime(bookingbean.bookingstarttime);
		booking.setBookingprice(bookingbean.bookingprice);
		booking.setUserid(bookingbean.userid);
		booking.setVehicleid(bookingbean.vehicleid);
		booking.setBookingstatus(bookingbean.bookingstatus);
		return bookingrepo.saveAndFlush(booking);
	}

	public List<BookingEntity> getBookingsForSelectedCar(long vehicleid) {
		return bookingrepo.findByVehicleid(vehicleid);
	}

	public List<BookingEntity> getBookingsForCustomer(int userid) {
		return bookingrepo.findByUserid(userid);
	}
	
	public void cancelBookingService(long bookingid) {
		bookingrepo.deleteById(bookingid);
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
