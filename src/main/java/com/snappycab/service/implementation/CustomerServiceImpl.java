package com.snappycab.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snappycab.dto.CustomerRequest;
import com.snappycab.dto.CustomerResponse;
import com.snappycab.dto.TripBookingResponse;
import com.snappycab.entity.Customer;
import com.snappycab.entity.Driver;
import com.snappycab.entity.TripBooking;
import com.snappycab.exceptions.MobileNumberAlreadyExistException;
import com.snappycab.exceptions.ResourseNotFoundException;
import com.snappycab.repository.CustomerRepo;
import com.snappycab.repository.DriverRepo;
import com.snappycab.repository.TripBookingRepo;
import com.snappycab.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private DriverRepo driverRepo;
	
	@Autowired
	private TripBookingRepo tripBookingRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CustomerResponse createCustomer(CustomerRequest customerRequest) {
		Customer customer = this.modelMapper.map(customerRequest, Customer.class);
		if(this.customerRepo.existsByMobileNumber(customer.getMobileNumber())) {
			throw new MobileNumberAlreadyExistException();
		}
		Customer savedCustomer = this.customerRepo.save(customer);
		return this.modelMapper.map(savedCustomer, CustomerResponse.class);
	}

	@Override
	public CustomerResponse updateCustomer(CustomerRequest customerRequest, String mobileNumber) {
		Customer customer = this.customerRepo.findCustomerByMobileNumber(mobileNumber);
		customer.setUserName(customerRequest.getUserName());
		customer.setAddress(customerRequest.getAddress());
		customer.setMobileNumber(customerRequest.getMobileNumber());
		customer.setEmail(customerRequest.getEmail());
		Customer updatedCustomer = this.customerRepo.save(customer);
		
		return this.modelMapper.map(updatedCustomer, CustomerResponse.class);
	}

	@Override
	public List<CustomerResponse> getAllCustomers() {
		List<Customer> customers = this.customerRepo.findAll();
		List<CustomerResponse> allCustomers = customers.stream().map(customer -> this.modelMapper.map(customer, CustomerResponse.class)).collect(Collectors.toList());
		return allCustomers;
	}

	@Override
	public void deleteCustoner(Integer customerId) {
		Customer customer = this.customerRepo.findById(customerId).orElseThrow(()-> new ResourseNotFoundException("Customer", "customerId", customerId));
		this.customerRepo.delete(customer);
	}

	@Override
	public CustomerResponse getSingleCustomer(Integer customerId) {
		Customer customer = this.customerRepo.findById(customerId).orElseThrow(()-> new ResourseNotFoundException("Customer", "customerId", customerId));
		
		return this.modelMapper.map(customer, CustomerResponse.class);
	}

	@Override
	public TripBookingResponse bookTrip(Integer tripBookingId, Integer driverId) {
		
		Driver driver = this.driverRepo.findById(driverId).orElseThrow(()-> new ResourseNotFoundException("Driver", "driverId", driverId));
		
		TripBooking tripBooking = this.tripBookingRepo.findById(tripBookingId).orElseThrow(()-> new ResourseNotFoundException("TripBooking", "tripBookingId", tripBookingId));
		
		tripBooking.setDriver(driver);
		tripBooking.setStatus(true);
		
		TripBooking tripBooked = this.tripBookingRepo.save(tripBooking);
		
		return this.modelMapper.map(tripBooked, TripBookingResponse.class);
	}
	
	

}
