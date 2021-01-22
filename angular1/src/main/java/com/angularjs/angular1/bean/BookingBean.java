package com.angularjs.angular1.bean;

import java.time.ZonedDateTime;

import org.springframework.stereotype.Component;

@Component
public class BookingBean {
	
	
	public long bookingid;
	public long userid;
	public long vehicleid;
	public int bookingduration;
	public ZonedDateTime bookingstarttime;
	public ZonedDateTime bookingendtime;
	public String bookingstatus;
	public long bookingprice;

}
