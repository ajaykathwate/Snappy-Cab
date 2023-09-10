package com.snappycab.controller;

import com.snappycab.dto.TripBookingRequest;
import com.snappycab.dto.TripBookingResponse;
import com.snappycab.payloads.ApiResponse;
import com.snappycab.service.TripBookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ajay Kathwate - 9/8/2023
 */

@RestController
@RequestMapping("/api/tripbooking")
public class TripBookingController {

    @Autowired
    private TripBookingService tripBookingService;

    // insert tripbooking by customer with customerId
    @PostMapping("/customer/{customerId}")
    public ResponseEntity<TripBookingResponse> insertTripBooking(@Valid @RequestBody TripBookingRequest tripBookingRequest, @PathVariable Integer customerId) {
        TripBookingResponse trip = this.tripBookingService.insertTripBooking(tripBookingRequest, customerId);
        return new ResponseEntity<TripBookingResponse>(trip, HttpStatus.CREATED);
    }

    ;

    // update tripbooking by tripbooking id
    @PutMapping("/{tripBookingId}")
    public ResponseEntity<TripBookingResponse> updateTripBooking(@Valid @RequestBody TripBookingRequest tripBookingRequest, @PathVariable Integer tripBookingId) {

        TripBookingResponse trip = this.tripBookingService.updateTripBooking(tripBookingRequest, tripBookingId);

        return new ResponseEntity<TripBookingResponse>(trip, HttpStatus.OK);
    }

    // delete tripbooking by tripBookingId
    @DeleteMapping("/{tripBookingId}")
    public ResponseEntity<ApiResponse> deleteTripBookingByTripBookingId(@PathVariable Integer tripBookingId) {
        this.tripBookingService.deleteTripBooking(tripBookingId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Trip Deleted Successfully...", true), HttpStatus.OK);
    }

    // view all trips by customer with customerId
    @GetMapping("/customer")
    public ResponseEntity<List<TripBookingResponse>> viewAllTripsByCustomer(@RequestParam Integer customerId) {
        List<TripBookingResponse> trips = this.tripBookingService.viewAllTripsByCustomer(customerId);
        return new ResponseEntity<List<TripBookingResponse>>(trips, HttpStatus.OK);
    }

    @GetMapping("/customer/{tripBookingId}")
    public ResponseEntity<TripBookingResponse> trioFinish(@PathVariable Integer tripBookingId) {
        TripBookingResponse trip = this.tripBookingService.tripFinish(tripBookingId);
        return new ResponseEntity<TripBookingResponse>(trip, HttpStatus.OK);
    }

}
