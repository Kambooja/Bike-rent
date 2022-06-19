package com.sha.serverbikemanagement.repository;

import com.sha.serverbikemanagement.model.Bike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BikeRepository extends JpaRepository<Bike, Long> {



}
