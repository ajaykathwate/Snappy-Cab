package com.snappycab.dto;

import java.time.LocalTime;

import com.snappycab.entity.Customer;
import com.snappycab.entity.Driver;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TripBookingResponse {
	
	private Integer tripBookingId;
	
	private String fromLocation;
	private String toLocation;
	
	private LocalTime fromDataTime;
	private LocalTime toDataTime;
	
	private boolean status;
	
	private float distanceInKm;
	
	private float bill;
	
	private Customer customer;

	private Driver driver;
	
}
