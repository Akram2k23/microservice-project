package com.tap.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tap.hotel.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String> {

}
