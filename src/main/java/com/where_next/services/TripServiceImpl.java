package com.where_next.services;

import com.where_next.entities.Trip;
import com.where_next.exceptions.EntityNotFoundException;
import com.where_next.repositories.TripRepository;
import com.where_next.validations.BaseValidator;
import com.where_next.validations.RemovalValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private BaseValidator<Trip> tripValidator;

    @Override
    public Trip getTripById(Long id) {
        return tripRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(BaseValidator.ENTITY_WITH_ID_NOT_FOUND_ERROR_MSG,
                tripValidator.type(), id));
    }

    @Override
    public Trip getTripByName(String name) {
        return tripRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException(BaseValidator.ENTITY_WITH_NAME_NOT_FOUND_ERROR_MSG,
                        tripValidator.type(), name));
    }

    @Override
    public List<Trip> getAllTrips() {
        List<Trip> allTrips = new ArrayList<>();
        tripRepository.findAll().forEach(allTrips::add);
        return allTrips;
    }

    @Override
    public Trip saveTrip(Trip trip) {
        tripValidator.validate(trip);

        return tripRepository.save(trip);
    }

    @Override
    public void deleteTrip(Long id) {
        ((RemovalValidator) tripValidator).validate(id);
        tripRepository.deleteById(id);
    }
}
