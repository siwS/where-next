package com.where_next.services;

import com.where_next.entities.Trip;
import com.where_next.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TripServiceImpl implements TripService{

    @Autowired
    private TripRepository tripRepository;

    @Override
    public List<Trip> getAllTrips() {
        List<Trip> allTrips = new ArrayList<>();
        tripRepository.findAll().forEach(allTrips::add);
        return allTrips;
    }

    @Override
    public void addSampleTrip(String name) {
        Trip trip = new Trip();
        trip.setName(name);
        tripRepository.save(trip);
    }
}
