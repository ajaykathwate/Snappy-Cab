package com.snappycab.service.implementation;

import java.util.List;

import org.modelmapper.ModelMapper;

import com.snappycab.dto.TripBookingRequest;
import com.snappycab.dto.TripBookingResponse;
import com.snappycab.repository.CustomerRepo;
import com.snappycab.repository.TripBookingRepo;
import com.snappycab.service.TripBookingService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TripBookingServiceImpl implements TripBookingService {
	
	private final TripBookingRepo tripBookingRepo;
	
	private final CustomerRepo customerRepo;
	
	private final ModelMapper modelMapper;

	@Override
	public TripBookingResponse insertTripBooking(TripBookingRequest tripBookingRequest, Integer customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TripBookingResponse updateTripBooking(TripBookingRequest tripBookingRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TripBookingResponse deleteTripBooking(Integer tripBookingId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TripBookingResponse> viewAllTripsByCustomer(Integer customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String calculateBill(Integer customerId, Integer tripBookingId) {
		// TODO Auto-generated method stub
		return null;
	}

}
