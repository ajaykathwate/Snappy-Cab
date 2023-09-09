package com.snappycab.controller;

import com.snappycab.dto.TripBookingRequest;
import com.snappycab.dto.TripBookingResponse;
import com.snappycab.service.TripBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ajay Kathwate - 9/8/2023
 */

@RestController
@RequestMapping("/api/tripbooking/")
public class TripBookingController {

    @Autowired
    private TripBookingService tripBookingService;

    // insert tripbooking by customer with customerId
    @PostMapping("/customer/{customerId}")
    public ResponseEntity<TripBookingResponse> insertTripBooking(@RequestBody TripBookingRequest tripBookingRequest, @PathVariable Integer customerId){
        TripBookingResponse trip = this.tripBookingService.insertTripBooking(tripBookingRequest, customerId);
        return new ResponseEntity<TripBookingResponse>(trip, HttpStatus.CREATED);
    };

    // update tripbooking by tripbooking id

    // delete tripbooking by tripBookingId

    // view all trips by customer with customerId

    // calculate trip bill using customerId and tripBookingId

}
