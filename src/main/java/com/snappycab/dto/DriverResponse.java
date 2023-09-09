package com.snappycab.dto;

import java.util.ArrayList;
import java.util.List;

import com.snappycab.entity.Cab;
import com.snappycab.entity.TripBooking;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DriverResponse {
	
	private Integer driverId;

	@Column(unique = true)
	@Size(min = 4, max = 25, message = "title must be of atleast 4 characters!")
	private String userName;

	@NotEmpty(message = "Email required!")
	@Email
	private String email;

	@NotEmpty
	@Size(min = 8, message = "password should be minimum 8 characters")
	private String password;

	@NotEmpty
	@Column(unique = true)
	@Pattern(regexp = "[6789]{1}[0-9]{9}", message = "Enter valid 10 digit mobile number")
	private String mobileNumber;

	@NotEmpty
	private String address;


	@NotEmpty
	@Pattern(regexp = "[A-Z||a-z]{2}[0-9]{5}", message = "Enter valid License number")
	private String licenceNo;

	@NotNull
	private boolean avalibilityStatus;

	@Max(value = 5)
	@Min(value = 1)
	private Float rating;

	private Cab cab;

	List<TripBooking> tripBookings = new ArrayList<>();
}
