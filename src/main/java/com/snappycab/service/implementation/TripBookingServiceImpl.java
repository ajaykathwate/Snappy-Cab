package com.snappycab.service.implementation;

import com.snappycab.dto.*;
import com.snappycab.entity.Cab;
import com.snappycab.entity.Customer;
import com.snappycab.entity.Driver;
import com.snappycab.entity.TripBooking;
import com.snappycab.exceptions.ResourseNotFoundException;
import com.snappycab.repository.CustomerRepo;
import com.snappycab.repository.DriverRepo;
import com.snappycab.repository.TripBookingRepo;
import com.snappycab.service.TripBookingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TripBookingServiceImpl implements TripBookingService {

    private final TripBookingRepo tripBookingRepo;

    private final CustomerRepo customerRepo;

    private final DriverRepo driverRepo;

    private final ModelMapper modelMapper;

    @Override
    public TripBookingResponse insertTripBooking(TripBookingRequest tripBookingRequest, Integer customerId) {

        TripBooking tripBooking = this.modelMapper.map(tripBookingRequest, TripBooking.class);

        Customer customer = this.customerRepo.findById(customerId).orElseThrow(() -> new ResourseNotFoundException("Customer", "customerId", customerId));

        List<TripBooking> bookings = customer.getTripBookings();

        // get drivers and assign driver who is available
        List<Driver> drivers = this.driverRepo.findAll();
        List<Driver> driverList = new ArrayList<>();
        Driver driver = null;
        for (Driver d : drivers) {
            if ((d.isAvalibilityStatus() == true && d.getCab() != null)) {
                System.out.println("added to driver list: " + d);
                driver = d;
                break;
            }
        }

        // adding customer to tripbooking
        tripBooking.setCustomer(customer);
        // set localtime to trip booking
        LocalTime localTime = LocalTime.now();
        tripBooking.setFromDataTime(localTime);

        // set driver to the trip & set driver status availavle = false
        driver.setAvalibilityStatus(false);
        // add this trip booking to the driver tripbooking list
        List<TripBooking> trips = driver.getTripBookings();
        trips.add(tripBooking);
        tripBooking.setDriver(driver);

        // calculate bill
        Cab cab = driver.getCab();
        tripBooking.setBill(cab.getPerKmRate() * tripBooking.getDistanceInKm());

        bookings.add(tripBooking);
        TripBooking savedTripBooking = this.tripBookingRepo.save(tripBooking);

        return this.modelMapper.map(savedTripBooking, TripBookingResponse.class);
    }

    @Override
    public TripBookingResponse updateTripBooking(TripBookingRequest tripBookingRequest, Integer tripBookingId) {

        TripBooking tripBooking = this.tripBookingRepo.findById(tripBookingId).orElseThrow(() -> new ResourseNotFoundException("TripBooking", "tripBookingId", tripBookingId));

        tripBooking.setFromLocation(tripBookingRequest.getFromLocation());
        tripBooking.setToLocation(tripBookingRequest.getToLocation());
        tripBooking.setStatus(tripBookingRequest.isStatus());
        tripBooking.setDistanceInKm(tripBookingRequest.getDistanceInKm());

        // calculate bill
        Driver driver = tripBooking.getDriver();
        Cab cab = driver.getCab();
        tripBooking.setDriver(driver);
        tripBooking.setBill(cab.getPerKmRate() * tripBooking.getDistanceInKm());

        TripBooking tripBookingUpdated = this.tripBookingRepo.save(tripBooking);

        return this.modelMapper.map(tripBookingUpdated, TripBookingResponse.class);
    }

    @Override
    public void deleteTripBooking(Integer tripBookingId) {
        TripBooking tripBooking = this.tripBookingRepo.findById(tripBookingId).orElseThrow(() -> new ResourseNotFoundException("TripBooking", "tripBookingId", tripBookingId));

        this.tripBookingRepo.delete(tripBooking);

    }

    @Override
    public List<TripBookingResponse> viewAllTripsByCustomer(Integer customerId) {
        // TODO Auto-generated method stub

        Customer customer = this.customerRepo.findById(customerId).orElseThrow(() -> new ResourseNotFoundException("Customer", "customerId", customerId));

        List<TripBooking> tripBookings = customer.getTripBookings();

        List<TripBookingResponse> allTripBokings = tripBookings.stream().map(booking -> this.modelMapper.map(booking, TripBookingResponse.class)).collect(Collectors.toList());

        return allTripBokings;
    }


    @Override
    public TripBookingResponse tripFinish(Integer tripBookingId) {
        TripBooking tripBooking = this.tripBookingRepo.findById(tripBookingId).orElseThrow(() -> new ResourseNotFoundException("TripBooking", "tripBookingId", tripBookingId));

        // calculate bill
        if (tripBooking.isStatus() == true) {
            Driver driver = tripBooking.getDriver();
            Cab cab = driver.getCab();

            tripBooking.setDriver(driver);
            tripBooking.setBill(cab.getPerKmRate() * tripBooking.getDistanceInKm());
            // set cab status to true as this trip is finished
            cab.setAvalibilityStatus(true);
            driver.setAvalibilityStatus(true);
            this.tripBookingRepo.save(tripBooking);
        }

        // set finish time
        LocalTime localTime = LocalTime.now();
        tripBooking.setToDataTime(localTime);
        TripBooking tripBookingUpdated = this.tripBookingRepo.save(tripBooking);

        return this.modelMapper.map(tripBookingUpdated, TripBookingResponse.class);
    }

}
