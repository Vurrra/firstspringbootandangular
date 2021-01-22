package com.angularjs.angular1.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angularjs.angular1.entity.VehicleEntity;

public interface VehicleDao extends JpaRepository<VehicleEntity, Long> {
    
	VehicleEntity findByVehiclenumber(String vehiclenumber);
	
	List<VehicleEntity> findByVendorid(long vendorid);

	
}
