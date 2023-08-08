package com.tap.rating.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tap.rating.entities.Rating;
import com.tap.rating.serviceimpl.RatingServiceImpl;

@RestController
@RequestMapping("/ratings")
public class RatingController {
	
	@Autowired
	private RatingServiceImpl ratingServiceImpl;
	
	//create rating
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping
	public ResponseEntity<Rating> create(@RequestBody Rating ratig){
		return ResponseEntity.status(HttpStatus.CREATED).body(ratingServiceImpl.create(ratig));
	}
	
	//get all
	
	@GetMapping
	public ResponseEntity<List<Rating>> getRatings(){
		return ResponseEntity.ok(ratingServiceImpl.getRatings());
	}
	
	//get rating by user id
	
	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
	@GetMapping("users/{userId}")
	public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId){
		return ResponseEntity.ok(ratingServiceImpl.getRatingByUserId(userId));
	}
	
	//get rating by hotel id
	
	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId){
		return ResponseEntity.ok(ratingServiceImpl.getRatingByHotelId(hotelId));
	}
	
	

}
