package com.snappycab.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.snappycab.dto.CabResponse;
import com.snappycab.dto.DriverResponse;
import com.snappycab.entity.Cab;
import com.snappycab.payloads.ApiResponse;
import com.snappycab.service.CabService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cab")
public class CabController {
	
	@Autowired
	private CabService cabService;
	
	@PostMapping("/")
	public ResponseEntity<CabResponse> registerCab(@Valid @RequestBody Cab cab){
		CabResponse registeredCab = this.cabService.registerCab(cab);
		return new ResponseEntity<CabResponse>(registeredCab, HttpStatus.CREATED);
	}
	
	@PutMapping("/{cabId}")
	public ResponseEntity<ApiResponse> updateCab(@Valid @RequestBody Cab cab, @PathVariable Integer cabId){
		
		CabResponse updatedCab = this.cabService.updateCab(cabId, cab);
		
		return  new ResponseEntity<ApiResponse>(new ApiResponse("Cab details updated successfully !!", true, updatedCab), HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CabResponse>> getAllCabs(){
		
		List<CabResponse> allCabs = this.cabService.getAllCabs();
		
		return new ResponseEntity<List<CabResponse>>(allCabs, HttpStatus.OK);
	}
	
	@DeleteMapping("/{cabId}")
	public ResponseEntity<ApiResponse> deleteCab(@PathVariable Integer cabId){
		
		this.cabService.deleteCab(cabId);
		
		return  new ResponseEntity<ApiResponse>(new ApiResponse("Cab deleted successfully !!", true),HttpStatus.OK);
	}
	
	@PatchMapping("/{cabId}")
	public ResponseEntity<ApiResponse> updateAvailibilityStatus(@Valid @PathVariable Integer cabId, Cab cab){
		CabResponse cabResponse = this.cabService.updateAvailibilityStatus(cabId, cab);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Availability status updated successfully !!", true, cabResponse), HttpStatus.OK);
	}
	
	@GetMapping("/driver/{cabId}")
	public ResponseEntity<DriverResponse> viewDriverByCabId(@PathVariable Integer cabId){
		DriverResponse driver = this.cabService.viewDriverByCabId(cabId);
		return new ResponseEntity<DriverResponse>(driver, HttpStatus.OK);
	}

}
