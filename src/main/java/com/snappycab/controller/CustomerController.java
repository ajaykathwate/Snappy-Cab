package com.snappycab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.snappycab.dto.CustomerRequest;
import com.snappycab.dto.CustomerResponse;
import com.snappycab.dto.TripBookingResponse;
import com.snappycab.payloads.ApiResponse;
import com.snappycab.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/")
	public ResponseEntity<ApiResponse> createCustomer(@Valid @RequestBody CustomerRequest customerRequest){
		CustomerResponse savedCustomer = this.customerService.createCustomer(customerRequest);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Customer created successfully !", true, savedCustomer), HttpStatus.CREATED);
	}
	
	@PutMapping("/{mobileNumber}")
	public ResponseEntity<ApiResponse> updateCustomer(@Valid @RequestBody CustomerRequest customerRequest, @PathVariable String mobileNumber){
		CustomerResponse updatedCustomer = this.customerService.updateCustomer(customerRequest, mobileNumber);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Customer details updated", true, updatedCustomer), HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CustomerResponse>> allCustomers(){
		List<CustomerResponse> customers = this.customerService.getAllCustomers();
		return new ResponseEntity<List<CustomerResponse>>(customers, HttpStatus.OK);
	}
	
	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerResponse> getCustomer(@PathVariable Integer customerId){
		CustomerResponse customer = this.customerService.getSingleCustomer(customerId);
		return new ResponseEntity<CustomerResponse>(customer, HttpStatus.OK);
	}
	
	@DeleteMapping("/{customerId}")
	public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable Integer customerId){
		this.customerService.deleteCustoner(customerId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Customer deleted" ,true), HttpStatus.OK);
	}
	
	@GetMapping("/booktrip")
	public ResponseEntity<TripBookingResponse> tripBooking(@RequestParam Integer tripBookingId, @RequestParam Integer driverId){
		TripBookingResponse tripBooking = this.customerService.bookTrip(tripBookingId, driverId);
		return new ResponseEntity<TripBookingResponse>(tripBooking, HttpStatus.OK);
	}
}
