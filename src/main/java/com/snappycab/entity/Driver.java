package com.snappycab.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Driver extends AbstractUser{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "driver_id")
	private Integer driverId;
	
	@NotEmpty
	@Pattern(regexp = "[A-Z||a-z]{2}[0-9]{5}", message = "Enter valid License number")
	private String licenceNo;
	
	@Max(value=5)
   	@Min(value=1)
	private Float rating;
	
	@JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cab_id" )
    private Cab cab;
	
	@JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "driver")
    List<TripBooking> tripBookings = new ArrayList<>();
	
	public Driver(String userName, @NotEmpty @Size(min = 8, message = "password should be minimum 8 characters") String password, @NotEmpty String address, @NotEmpty @Pattern(regexp = "[6789]{1}[0-9]{9}", message = "Enter valid 10 digit mobile number") String mobileNumber, @NotEmpty @Email String email, String licenceNo, Float rating) {
        super(userName, password, address, mobileNumber, email);
        this.licenceNo = licenceNo;
        this.rating = rating;
    }
	

}
