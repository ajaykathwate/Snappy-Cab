package com.snappycab.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.snappycab.dto.CabResponse;
import com.snappycab.dto.DriverRequest;
import com.snappycab.dto.DriverResponse;
import com.snappycab.payloads.ApiResponse;
import com.snappycab.service.DriverService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/driver")
public class DriverController {
	
	@Autowired
	private DriverService driverService;
	
	@PostMapping("/")
	public ResponseEntity<ApiResponse> registerDriver(@Valid @RequestBody DriverRequest driverRequest){
		DriverResponse driver = this.driverService.registerDriver(driverRequest);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Driver registered successfully !", true, driver), HttpStatus.CREATED);
	}
	
	@PutMapping("/{driverId}")
	public ResponseEntity<ApiResponse> updateDriver(@Valid @RequestBody DriverRequest driverRequest, @PathVariable Integer driverId){
		DriverResponse UpdatedDriver = this.driverService.updateDriver(driverId, driverRequest);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Driver details updated !", true, UpdatedDriver), HttpStatus.OK);
	}
	
	@GetMapping("/{driverId}")
	public DriverResponse getDriverById(@PathVariable Integer driverId) {
		DriverResponse driver = this.driverService.getDriverById(driverId);
		return driver;
	}
	
	@DeleteMapping("/{driverId}")
	public ResponseEntity<ApiResponse> deleteDriverById(@PathVariable Integer driverId) {
		this.driverService.deleteDriverById(driverId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Driver Deleted successfully !", true), HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<DriverResponse>> gelAllDrivers(){
		List<DriverResponse> allDrivers = this.driverService.getAllDrivers();
		return new ResponseEntity<List<DriverResponse>>(allDrivers, HttpStatus.OK);
	}
	
	@GetMapping("/username/{userName}")
	public ResponseEntity<DriverResponse> getDriverByName(@PathVariable String userName){
		DriverResponse driver = this.driverService.getDriverByName(userName);
		return new ResponseEntity<DriverResponse>(driver, HttpStatus.OK);
	}
	
	// TODO not able to find cab using the driverId
	@GetMapping("/cab")
	public ResponseEntity<CabResponse> viewCabByDriverId(@RequestParam Integer driverId){
		CabResponse cab = this.driverService.viewCabByDriverId(driverId);
		return new ResponseEntity<CabResponse>(cab, HttpStatus.OK);
	}
	
	@PatchMapping("/allocate/cab")
	public ResponseEntity<Map<DriverResponse, CabResponse>> allocateCabToDriver(@RequestParam Integer cabId, @RequestParam Integer driverId){
		Map<DriverResponse, CabResponse> map = this.driverService.allocateCabToDriver(driverId, cabId);
		return new ResponseEntity<Map<DriverResponse,CabResponse>>(map, HttpStatus.OK);
	}
	
	

}
