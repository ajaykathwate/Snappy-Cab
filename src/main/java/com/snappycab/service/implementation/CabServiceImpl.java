package com.snappycab.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snappycab.dto.CabResponse;
import com.snappycab.dto.DriverResponse;
import com.snappycab.entity.Cab;
import com.snappycab.entity.Driver;
import com.snappycab.exceptions.ResourseNotFoundException;
import com.snappycab.exceptions.SameStatusAsPrevException;
import com.snappycab.repository.CabRepo;
import com.snappycab.repository.DriverRepo;
import com.snappycab.service.CabService;

@Service
public class CabServiceImpl implements CabService {
	@Autowired
	private CabRepo cabRepo;

	@Autowired
	private DriverRepo driverRepo;

	@Autowired
	private ModelMapper modelMapper;

	// Register Cab
	@Override
	public CabResponse registerCab(Cab cab) {
		Cab cabRegistered = this.cabRepo.save(cab);
		return this.modelMapper.map(cabRegistered, CabResponse.class);
	}

	// update cab
	@Override
	public CabResponse updateCab(Integer cabId, Cab cabRequest) {
		// TODO Auto-generated method stub
		// throw error using
		Cab cab = this.cabRepo.findById(cabId).orElseThrow(() -> new ResourseNotFoundException("Cab", "cabId", cabId));
		cab.setAvalibilityStatus(cabRequest.isAvalibilityStatus());
		cab.setCabType(cabRequest.getCabType());
		cab.setPerKmRate(cabRequest.getPerKmRate());

		Cab updatedCab = this.cabRepo.save(cab);

		return this.modelMapper.map(updatedCab, CabResponse.class);
	}

	// get all cabs
	@Override
	public List<CabResponse> getAllCabs() {
		List<Cab> cabs = this.cabRepo.findAll();

		List<CabResponse> allCabs = cabs.stream().map(cab -> this.modelMapper.map(cab, CabResponse.class))
				.collect(Collectors.toList());

		return allCabs;
	}

	// delete a cab by cabId
	@Override
	public void deleteCab(Integer cabId) {
		Cab cab = this.cabRepo.findById(cabId).orElseThrow(() -> new ResourseNotFoundException("Cab", "cabId", cabId));
		this.cabRepo.delete(cab);
	}

	@Override
	public DriverResponse viewDriverByCabId(Integer cabId) {
		// TODO Auto-generated method stub
		Driver driver = this.driverRepo.findById(cabId)
				.orElseThrow(() -> new ResourseNotFoundException("Driver", "cabId", cabId));

		return this.modelMapper.map(driver, DriverResponse.class);
	}

	// update status of cab
	@Override
	public CabResponse updateAvailibilityStatus(Integer cabId, Cab cabResponse) {
		Cab cab = this.cabRepo.findById(cabId).orElseThrow(() -> new ResourseNotFoundException("Cab", "cabId", cabId));
		if (cab.isAvalibilityStatus() == cabResponse.isAvalibilityStatus()) {
			throw new SameStatusAsPrevException("Status can not be same as previous !");
		}
		cab.setAvalibilityStatus(cabResponse.isAvalibilityStatus());
		Cab updatedCab = this.cabRepo.save(cab);
		return this.modelMapper.map(updatedCab, CabResponse.class);
	}

}
