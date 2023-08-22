package com.snappycab.entity;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor 
public class TripBooking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer tripBookingId;
	
	private String fromLocation;
	private String toLocation;
	
	private LocalTime fromDataTime;
	private LocalTime toDataTime;
	
	private boolean status;
	
	private float distanceInKm;
	
	private float bill;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "customerId")
	private Customer customer;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "driverId")
	private Driver driver;

}
