package com.angularjs.angular1.entity;

import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "bookingid", initialValue = 100005, allocationSize = 1)
public class BookingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookingid")
	private long bookingid;
	private long userid;
	private long vehicleid;
	private int bookingduration;
	private ZonedDateTime bookingstarttime;
	private ZonedDateTime bookingendtime;
	private String bookingstatus;
	private long bookingprice;

	public long getBookingprice() {
		return bookingprice;
	}

	public void setBookingprice(long bookingprice) {
		this.bookingprice = bookingprice;
	}

	public long getBookingid() {
		return bookingid;
	}

	public void setBookingid(long bookingid) {
		this.bookingid = bookingid;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public long getVehicleid() {
		return vehicleid;
	}

	public void setVehicleid(long vehicleid) {
		this.vehicleid = vehicleid;
	}

	public int getBookingduration() {
		return bookingduration;
	}

	public void setBookingduration(int bookingduration) {
		this.bookingduration = bookingduration;
	}

	public ZonedDateTime getBookingstarttime() {
		return bookingstarttime;
	}

	public void setBookingstarttime(ZonedDateTime bookingstarttime) {
		this.bookingstarttime = bookingstarttime;
	}

	public ZonedDateTime getBookingendtime() {
		return bookingendtime;
	}

	public void setBookingendtime(ZonedDateTime bookingendtime) {
		this.bookingendtime = bookingendtime;
	}

	public String getBookingstatus() {
		return bookingstatus;
	}

	public void setBookingstatus(String bookingstatus) {
		this.bookingstatus = bookingstatus;
	}

	@Override
	public String toString() {
		return "BookingEntity [bookingid=" + bookingid + ", userid=" + userid + ", vehicleid=" + vehicleid
				+ ", bookingduration=" + bookingduration + ", bookingstarttime=" + bookingstarttime
				+ ", bookingendtime=" + bookingendtime + ", bookingstatus=" + bookingstatus 
				+ ", bookingprice= " + bookingprice + "]";
	}

}
