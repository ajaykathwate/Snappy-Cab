package com.snappycab.dto;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
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

	// set at runtime while booking trip
	@JsonFormat(pattern = "hh:mm:ss a")
	private LocalTime fromDataTime;

	// set  when the trip is done
	@JsonFormat(pattern = "hh:mm:ss")
	private LocalTime toDataTime;
	
	@NotNull
	private boolean status;
	
	@NotEmpty
	private float distanceInKm;

	private float bill;

	private Customer customer;
	
	private Driver driver;

}
