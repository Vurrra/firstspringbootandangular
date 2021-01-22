package com.angularjs.angular1.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angularjs.angular1.entity.FeedbackEntity;

public interface FeedbackDao extends JpaRepository<FeedbackEntity, Integer> {

	List<FeedbackEntity> findByUserid(int userid);
	
	FeedbackEntity findByVehicleid(long vehicleid);
	 
}
