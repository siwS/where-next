package com.where_next.services;

import com.where_next.entities.Trip;

import java.util.List;

public interface TripService {
    Trip getTripById(Long id);
    Trip getTripByName(String name);
    List<Trip> getAllTrips();
    Trip saveTrip(Trip trip);
    void deleteTrip(Long id);
}
