package com.snappycab.service;

import java.util.List;
import java.util.Map;

import com.snappycab.dto.CabResponse;
import com.snappycab.dto.DriverRequest;
import com.snappycab.dto.DriverResponse;

public interface DriverService {
	
	DriverResponse registerDriver(DriverRequest driverResRequest);
	
	DriverResponse updateDriver(Integer driverId, DriverRequest driverRequest);
	
	DriverResponse getDriverById(Integer driverId);

	DriverResponse getDriverByName(String userName);
	
	CabResponse viewCabByDriverId(Integer driverId);
	
	Map<DriverResponse, CabResponse> allocateCabToDriver(Integer driverId, Integer cabId);
	
	List<DriverResponse> getAllDrivers();
	
	void deleteDriverById(Integer driverId);
	
	

}
