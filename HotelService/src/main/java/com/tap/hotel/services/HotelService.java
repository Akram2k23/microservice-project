package com.tap.hotel.services;

import java.util.List;

import com.tap.hotel.entities.Hotel;

public interface HotelService {
	
	
	
	Hotel create(Hotel hotel);
	
	
	List<Hotel> getAll();
	
	
	Hotel get(String hotelId);

}
