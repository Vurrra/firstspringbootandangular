package com.angularjs.angular1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.angularjs.angular1.dao.BookingDao;
import com.angularjs.angular1.dao.FeedbackDao;
import com.angularjs.angular1.dao.UserDao;
import com.angularjs.angular1.dao.VehicleDao;
import com.angularjs.angular1.entity.BookingEntity;
import com.angularjs.angular1.entity.FeedbackEntity;
import com.angularjs.angular1.entity.UserEntity;
import com.angularjs.angular1.entity.VehicleEntity;

@Component
public class AdminActionsService {
	
	@Autowired
	VehicleDao vehiclerepo;
	
	@Autowired
	UserDao userrepo;
	
	@Autowired
	BookingDao bookingrepo;
	
	@Autowired
	FeedbackDao feedbackrepo;
	
	public List<VehicleEntity> getAllVehicles(){
		return vehiclerepo.findAll();
	}
	
	public List<UserEntity> getAllUsers(){
		return userrepo.findAll();
	}
	
	public List<BookingEntity> getAllBookings(){
		return bookingrepo.findAll();
	}
	
	public List<FeedbackEntity> getAllFeedbacks(){
		return feedbackrepo.findAll();
	}

}
