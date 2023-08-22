package com.snappycab.dto;

import com.snappycab.entity.Driver;

import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CabResponse {
	private Integer cabId;
	
	private String cabType;

	private float perKmRate;
	
	private boolean avalibilityStatus;
	
	@JoinColumn(name = "driver_id")
    private Driver driver;

}
