package com.sha.serverbikemanagement.repository;

import com.sha.serverbikemanagement.model.Bike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BikeRepository  extends JpaRepository<Bike,Long> {


    @Override
    Optional<Bike> findById(Long aLong);
}
