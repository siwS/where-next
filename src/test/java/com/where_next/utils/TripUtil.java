package com.where_next.utils;

import com.where_next.entities.Trip;
import com.where_next.repositories.TripRepository;
import com.where_next.services.TripService;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class TripUtil {

    public static final Long TRIP_ID_1 = 1L;
    public static final Long TRIP_ID_2 = 2L;
    public static final String FIRST_TRIP_NAME = "First test trip";
    public static final String SECOND_TRIP_NAME = "Second test trip";

    private static Trip firstTrip = TripUtil.createTrip(TRIP_ID_1, FIRST_TRIP_NAME);
    private static Trip secondTrip = TripUtil.createTrip(TRIP_ID_2, SECOND_TRIP_NAME);

    public static void setUpTripRepositoryMock(TripRepository tripRepository) {
        when(tripRepository.findById(TRIP_ID_1)).thenReturn(Optional.of(firstTrip));
        when(tripRepository.findById(TRIP_ID_2)).thenReturn(Optional.of(secondTrip));
        when(tripRepository.findByName(FIRST_TRIP_NAME)).thenReturn(Optional.of(firstTrip));
        when(tripRepository.findByName(SECOND_TRIP_NAME)).thenReturn(Optional.of(secondTrip));
        when(tripRepository.findAll()).thenReturn(List.of(firstTrip, secondTrip));
    }

    public static void setupTripServiceMock(TripService tripService) {
        when(tripService.getTripById(TRIP_ID_1)).thenReturn(firstTrip);
        when(tripService.getTripById(TRIP_ID_2)).thenReturn(secondTrip);
        when(tripService.getTripByName(FIRST_TRIP_NAME)).thenReturn(firstTrip);
        when(tripService.getTripByName(SECOND_TRIP_NAME)).thenReturn(secondTrip);
    }

    public static Trip createTrip(long id, String name) {
        Trip trip = new Trip();
        trip.setId(id);
        trip.setName(name);

        return trip;
    }
}
