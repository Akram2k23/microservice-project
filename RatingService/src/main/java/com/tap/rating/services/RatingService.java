package com.tap.rating.services;

import java.util.List;

import com.tap.rating.entities.Rating;

public interface RatingService {
	
	//create
	Rating create(Rating rating);
	
	//get all rating
	List<Rating> getRatings();
	
	//get all by userId
	List<Rating> getRatingByUserId(String userId);
	
	
	//get all by hotelId
	List<Rating> getRatingByHotelId(String userId);
	
}
