package com.snappycab.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import com.snappycab.entity.Cab;
import com.snappycab.entity.Customer;
import com.snappycab.entity.Driver;
import com.snappycab.entity.TripBooking;
import com.snappycab.exceptions.NoTripBookingFoundForThisCustomerException;
import com.snappycab.exceptions.ResourseNotFoundException;
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

		TripBooking tripBooking = this.modelMapper.map(tripBookingRequest, TripBooking.class);

		Customer customer = this.customerRepo.findById(customerId).orElseThrow(()-> new ResourseNotFoundException("Customer", "customerId", customerId));

		List<TripBooking> bookings =customer.getTripBookings();

		tripBooking.setCustomer(customer);

		bookings.add(tripBooking);
		TripBooking savedTripBooking = this.tripBookingRepo.save(tripBooking);

		return this.modelMapper.map(savedTripBooking, TripBookingResponse.class);
	}

	@Override
	public TripBookingResponse updateTripBooking(TripBookingRequest tripBookingRequest, Integer tripBookingId) {

		TripBooking tripBooking  = this.tripBookingRepo.findById(tripBookingId).orElseThrow(()-> new ResourseNotFoundException("TripBooking", "tripBookingId", tripBookingId));

		tripBooking.setFromLocation(tripBookingRequest.getFromLocation());
		tripBooking.setToLocation(tripBookingRequest.getToLocation());
		tripBooking.setFromDataTime(tripBookingRequest.getFromDataTime());
		tripBooking.setToDataTime(tripBookingRequest.getToDataTime());
		tripBooking.setToDataTime(tripBookingRequest.getToDataTime());
		tripBooking.setToDataTime(tripBookingRequest.getToDataTime());

		TripBooking tripBookingUpdated = this.tripBookingRepo.save(tripBooking);

		return this.modelMapper.map(tripBookingUpdated, TripBookingResponse.class);
	}

	@Override
	public void deleteTripBooking(Integer tripBookingId) {
		TripBooking tripBooking  = this.tripBookingRepo.findById(tripBookingId).orElseThrow(()-> new ResourseNotFoundException("TripBooking", "tripBookingId", tripBookingId));

		this.tripBookingRepo.delete(tripBooking);

	}

	@Override
	public List<TripBookingResponse> viewAllTripsByCustomer(Integer customerId) {
		// TODO Auto-generated method stub

		Customer customer = this.customerRepo.findById(customerId).orElseThrow(()-> new ResourseNotFoundException("Customer", "customerId", customerId));

		List<TripBooking> tripBookings = customer.getTripBookings();

		List<TripBookingResponse> allTripBokings = tripBookings.stream().map(booking -> this.modelMapper.map(booking, TripBookingResponse.class)).collect(Collectors.toList());

		return allTripBokings;
	}

	@Override
	public String calculateBill(Integer customerId, Integer tripBookingId) {
		// TODO Auto-generated method stub

		TripBooking tripBooking = this.tripBookingRepo.findById(tripBookingId).orElseThrow(()-> new ResourseNotFoundException("TripBooking","tripBookingId", tripBookingId));

		if(tripBooking.isStatus() == true){
			Driver driver = tripBooking.getDriver();
			Cab cab = driver.getCab();

			tripBooking.setDriver(driver);
			tripBooking.setBill(cab.getPerKmRate() * tripBooking.getDistanceInKm());

			this.tripBookingRepo.save(tripBooking);

		}

		return "Total Trip Bill for Trip ID " + tripBooking.getTripBookingId() + " is : " + tripBooking.getBill();
	}

}
