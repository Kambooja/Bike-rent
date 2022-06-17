package com.sha.serverbikemanagement.service;

import com.sha.serverbikemanagement.model.Bike;

import java.util.List;

public interface BikeService {
    Bike saveBike (Bike bike);

    Bike updateBike(Bike bike);

    void deleteBike(Long bikeid);

    List<Bike> findAllBikes();

    Long numberOfBikes();
}
