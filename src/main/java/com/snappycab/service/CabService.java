package com.snappycab.service;

import java.util.List;

import com.snappycab.dto.CabResponse;
import com.snappycab.dto.DriverResponse;
import com.snappycab.entity.Cab;

public interface CabService {
	
	CabResponse registerCab(Cab cab);
	
	CabResponse updateCab(Integer cabId, Cab cab);
	
	List<CabResponse> getAllCabs();
	
	void deleteCab(Integer cabId);
	
	CabResponse updateAvailibilityStatus(Integer cabId, Cab cabResponse);
	
	DriverResponse viewDriverByCabId(Integer cabId);	

}
