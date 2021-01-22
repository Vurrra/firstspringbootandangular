package com.angularjs.angular1.services;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.angularjs.angular1.dao.BookingDao;
import com.angularjs.angular1.dao.VehicleDao;
import com.angularjs.angular1.entity.BookingEntity;

@Component
public class BookingsAndVehicleRefreshService {
	
	@Autowired
	BookingDao bookingrepo;
	
	@Autowired
	VehicleDao vehiclerepo;
	
	public void refreshBookingStatuses(){
		try {
			List<BookingEntity> allbookings=bookingrepo.findAll();
			ZonedDateTime dateandtime=ZonedDateTime.now();
			for(BookingEntity booking : allbookings) {
				if(booking.getBookingendtime().isBefore(dateandtime)) {
					try {
						bookingrepo.delete(booking);
					}catch (NoSuchElementException e) {
						System.out.println(e);
					}
				}else if(booking.getBookingendtime().isAfter(dateandtime)) {
					try {
					}catch (NoSuchElementException e) {
						System.out.println(e);
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
}
