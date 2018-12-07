package com.where_next.services;

import com.where_next.entities.Trip;

import java.util.List;

public interface TripService {
    public List<Trip> getAllTrips();
    public void addSampleTrip(String name);
}
