package com.snappycab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snappycab.entity.Driver;

public interface DriverRepo extends JpaRepository<Driver, Integer>{
	
	Driver getDriverByUserName(String userName);

}
