package com.snappycab.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@MappedSuperclass
public abstract class AbstractUser {
	
	// this class/entity have common fields required for other entities.
	// this AbstractUser can be extended by any class to use these fields
	
	@Column(unique = true)
	@Size(min=4, max=25, message= "title must be of atleast 4 characters!")
    private String userName;
	
	@NotEmpty(message="Email required!")
	@Email
    private String email;
  
	@NotEmpty
	@Size(min = 8,message="password should be minimum 8 characters")
    private String password;
	
	@NotEmpty
  	@Column(unique = true)
  	@Pattern(regexp = "[6789]{1}[0-9]{9}", message = "Enter valid 10 digit mobile number")
    private String mobileNumber;
    
    @NotEmpty
    private String address;

}
