package com.snappycab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snappycab.entity.TripBooking;

public interface TripBookingRepo extends JpaRepository<TripBooking, Integer> {

}
