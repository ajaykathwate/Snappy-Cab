package com.snappycab.dto;

import java.util.ArrayList;
import java.util.List;

import com.snappycab.entity.TripBooking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {

	private Integer customerId;
	
	private String userName;
	
    private String email;
  
    private String password;
	
    private String mobileNumber;
    
    private String address;
	
	List<TripBooking> tripBookings = new ArrayList<>();
	
}
