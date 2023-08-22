package com.snappycab.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DriverRequest {
	
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
	
	@NotEmpty
	@Pattern(regexp = "[A-Z||a-z]{2}[0-9]{5}", message = "Enter valid License number")
	private String licenceNo;

}
