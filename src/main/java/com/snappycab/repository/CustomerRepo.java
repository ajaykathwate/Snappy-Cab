package com.snappycab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snappycab.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
	
	Customer findCustomerByMobileNumber(String mobileNumber);
	
	boolean existsByMobileNumber(String mobileNumber);

}
