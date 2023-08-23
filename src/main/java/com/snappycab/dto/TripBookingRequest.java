package com.snappycab.dto;

import java.time.LocalTime;

import com.snappycab.entity.Customer;
import com.snappycab.entity.Driver;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TripBookingRequest {
	
	@NotEmpty
	private String fromLocation;
	@NotEmpty
	private String toLocation;
	
	@NotEmpty
	private LocalTime fromDataTime;
	@NotEmpty
	private LocalTime toDataTime;
	
	@NotNull
	private boolean status;
	
	@NotEmpty
	private float distanceInKm;
	
	@NotEmpty
	private float bill;
	
	
	private Customer customer;
	
	private Driver driver;

}
