package com.angularjs.angular1.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "vehicleid", initialValue = 10006, allocationSize = 1)
public class VehicleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicleid")
	private long vehicleid;
	@Column(name = "vehiclenumber", unique = true)
	private String vehiclenumber;
	private String vehiclename;
	private long vendorid;
	private String bookingstatus;
	private int price;

	public long getVehicleid() {
		return vehicleid;
	}

	public void setVehicleid(long vehicleid) {
		this.vehicleid = vehicleid;
	}

	public String getVehiclenumber() {
		return vehiclenumber;
	}

	public void setVehiclenumber(String vehiclenumber) {
		this.vehiclenumber = vehiclenumber;
	}

	public String getVehiclename() {
		return vehiclename;
	}

	public void setVehiclename(String vehiclename) {
		this.vehiclename = vehiclename;
	}

	public long getVendorid() {
		return vendorid;
	}

	public void setVendorid(long vendorid) {
		this.vendorid = vendorid;
	}

	public String getBookingstatus() {
		return bookingstatus;
	}

	public void setBookingstatus(String bookingstatus) {
		this.bookingstatus = bookingstatus;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "VehicleEntity [vehicleid=" + vehicleid + ", vehiclenumber=" + vehiclenumber + ", vehiclename="
				+ vehiclename + ", vendorid=" + vendorid + ", bookingstatus=" + bookingstatus + ", price=" + price
				+ "]";
	}

}
