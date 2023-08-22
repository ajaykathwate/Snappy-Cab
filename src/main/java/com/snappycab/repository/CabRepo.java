package com.snappycab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snappycab.entity.Cab;

public interface CabRepo extends JpaRepository<Cab, Integer> {

}
