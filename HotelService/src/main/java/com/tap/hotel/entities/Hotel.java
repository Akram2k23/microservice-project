package com.tap.hotel.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Hotel {
	
	@Id
	private String hotelId;
	private String hotelName;
	private String location;
	private String about;
	
	public Hotel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Hotel(String hotelId, String hotelName, String location, String about) {
		super();
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.location = location;
		this.about = about;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	@Override
	public String toString() {
		return "Hotel [id=" + hotelId + ", name=" + hotelName + ", location=" + location + ", about=" + about + "]";
	}
	
	
	

}
