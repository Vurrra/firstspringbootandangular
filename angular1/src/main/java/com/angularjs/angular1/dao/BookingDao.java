package com.angularjs.angular1.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.angularjs.angular1.entity.BookingEntity;

public interface BookingDao extends JpaRepository<BookingEntity, Long> {

	List<BookingEntity> findByVehicleid(long vehicleid);

	@Query(value = "select * from Booking_entity b where b.vehicleid in (select vehicleid from Vehicle_entity v where v.vendorid = ?1)",nativeQuery = true )
	List<BookingEntity> findByVendorid(long vendorid);
	
	List<BookingEntity> findByUserid(long userid);
	
	@Transactional
	@Modifying
	@Query(value = "update Booking_entity b set b.bookingstatus = ?1, b.vehicleid = ?2, b.bookingprice = ?3 where b.bookingid = ?4", nativeQuery = true)
	void updateBookingById(String bookingstatus, long vehicleid, long price, long bookingid );
}
