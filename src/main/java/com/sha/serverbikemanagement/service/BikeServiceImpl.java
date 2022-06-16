package com.sha.serverbikemanagement.service;


import com.sha.serverbikemanagement.model.Bike;
import com.sha.serverbikemanagement.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BikeServiceImpl implements BikeService{

    @Autowired
    private BikeRepository bikeRepository;

    @Override
    public Bike saveBike(final Bike bike){
        bikeRepository.save(bike);
        return bike;
    }

    @Override
    public Bike updateBike(final Bike bike){
        return bikeRepository.save(bike);
    }

    @Override
    public void deleteBike(final Long bikeId){
        bikeRepository.deleteById(bikeId);
    }

    @Override
    public List<Bike> findAllBikes(){
        return bikeRepository.findAll();
    }
    @Override
    public Long numberOfBikes(){
        return bikeRepository.count();
    }
}
