package com.snappycab.service;

import java.util.List;

import com.snappycab.dto.TripBookingRequest;
import com.snappycab.dto.TripBookingResponse;

public interface TripBookingService {
	
	TripBookingResponse insertTripBooking(TripBookingRequest tripBookingRequest, Integer customerId);
	
	TripBookingResponse updateTripBooking(TripBookingRequest tripBookingRequest, Integer tripBookingId);
	
	void deleteTripBooking(Integer tripBookingId);
	
	List<TripBookingResponse> viewAllTripsByCustomer(Integer customerId);
	
	String calculateBill(Integer customerId, Integer tripBookingId);

}
