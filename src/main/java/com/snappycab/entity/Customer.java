package com.snappycab.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends AbstractUser{
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
	List<TripBooking> tripBookings = new ArrayList<>();
	
	public Customer(String userName, String password, String address, String mobileNumber, String email) {
		super(userName, password, address, mobileNumber, email);
		// TODO Auto-generated constructor stub
	}

}
