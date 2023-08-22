package com.snappycab.service;

import java.util.List;

import com.snappycab.dto.CustomerRequest;
import com.snappycab.dto.CustomerResponse;
import com.snappycab.dto.TripBookingResponse;

public interface CustomerService {
	
	CustomerResponse createCustomer(CustomerRequest customerRequest);
	
	CustomerResponse updateCustomer(CustomerRequest customerRequest, String mobileNumber);
	
	List<CustomerResponse> getAllCustomers();
	
	void deleteCustoner(Integer customerId);
	
	CustomerResponse getSingleCustomer(Integer customerId);
	
	TripBookingResponse bookTrip(Integer tripBookingId, Integer driverId);

}
