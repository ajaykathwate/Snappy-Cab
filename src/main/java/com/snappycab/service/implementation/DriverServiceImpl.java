package com.snappycab.service.implementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snappycab.dto.CabResponse;
import com.snappycab.dto.DriverRequest;
import com.snappycab.dto.DriverResponse;
import com.snappycab.entity.Cab;
import com.snappycab.entity.Driver;
import com.snappycab.exceptions.ResourseNotFoundException;
import com.snappycab.repository.CabRepo;
import com.snappycab.repository.DriverRepo;
import com.snappycab.service.DriverService;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	private DriverRepo driverRepo;

	@Autowired
	private CabRepo cabRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public DriverResponse registerDriver(DriverRequest driverResRequest) {
		Driver driver = this.modelMapper.map(driverResRequest, Driver.class);
		Driver savedDriver = this.driverRepo.save(driver);
		return this.modelMapper.map(savedDriver, DriverResponse.class);
	}

	@Override
	public DriverResponse updateDriver(Integer driverId, DriverRequest driverRequest) {
		Driver driver = this.driverRepo.findById(driverId)
				.orElseThrow(() -> new ResourseNotFoundException("Driver", "driverId", driverId));
		driver.setAddress(driverRequest.getAddress());
		driver.setEmail(driverRequest.getEmail());
		driver.setLicenceNo(driverRequest.getLicenceNo());
		driver.setMobileNumber(driverRequest.getMobileNumber());
		driver.setUserName(driverRequest.getUserName());
		Driver updatedDriver = this.driverRepo.save(driver);
		return this.modelMapper.map(updatedDriver, DriverResponse.class);
	}

	@Override
	public DriverResponse getDriverById(Integer driverId) {
		Driver driver = this.driverRepo.findById(driverId)
				.orElseThrow(() -> new ResourseNotFoundException("Driver", "driverId", driverId));

		return this.modelMapper.map(driver, DriverResponse.class);
	}

	@Override
	public DriverResponse getDriverByName(String userName) {
		// TODO Auto-generated method stub
		Driver driver = this.driverRepo.getDriverByUserName(userName);
		if (driver == null) {
			throw new ResourseNotFoundException("Driver", "userName" + userName, 0);
		}
		return this.modelMapper.map(driver, DriverResponse.class);
	}

	@Override
	public CabResponse viewCabByDriverId(Integer driverId) {
		// TODO Auto-generated method stub
		Cab cab = this.cabRepo.findById(driverId)
				.orElseThrow(() -> new ResourseNotFoundException("Cab", "driverId", driverId));

		return this.modelMapper.map(cab, CabResponse.class);
	}

	@Override
	public Map<DriverResponse, CabResponse> allocateCabToDriver(Integer driverId, Integer cabId) {
		Driver driver = this.driverRepo.findById(driverId)
				.orElseThrow(() -> new ResourseNotFoundException("Driver", "driverId", driverId));
		Cab cab = this.cabRepo.findById(cabId).orElseThrow(() -> new ResourseNotFoundException("Cab", "cabId", cabId));

		driver.setCab(cab);
		cab.setDriver(driver);
		Driver savedDriver = this.driverRepo.save(driver);
		Cab savedCab = this.cabRepo.save(cab);
		Map<DriverResponse, CabResponse> map = new HashMap<>();
		map.put(this.modelMapper.map(savedDriver, DriverResponse.class),
				this.modelMapper.map(savedCab, CabResponse.class));

		return map;
	}

	@Override
	public List<DriverResponse> getAllDrivers() {
		List<Driver> drivers = this.driverRepo.findAll();
		List<DriverResponse> allDrivers = drivers.stream()
				.map(driver -> this.modelMapper.map(driver, DriverResponse.class)).collect(Collectors.toList());

		return allDrivers;
	}

	@Override
	public void deleteDriverById(Integer driverId) {
		// TODO Auto-generated method stub
		Driver driver = this.driverRepo.findById(driverId)
				.orElseThrow(() -> new ResourseNotFoundException("Driver", "driverId", driverId));
		this.driverRepo.delete(driver);
	}

}
