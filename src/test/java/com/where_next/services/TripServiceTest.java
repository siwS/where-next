package com.where_next.services;

import com.where_next.entities.Trip;
import com.where_next.exceptions.DuplicateEntityException;
import com.where_next.exceptions.EntityNotFoundException;
import com.where_next.exceptions.InvalidInputParametersException;
import com.where_next.repositories.TripRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TripServiceTest {

    private static final Long TRIP_ID_1 = 1L;
    private static final Long TRIP_ID_2 = 2L;
    private static final Long NON_EXISTING_TRIP_ID = 22222L;
    private static final String NON_EXISTING_TRIP_NAME = "Non existing trip name";
    private static final String EXPECTED_FIRST_TRIP_NAME = "First test trip";
    private static final String EXPECTED_SECOND_TRIP_NAME = "Second test trip";
    private static final String EXPECTED_EXCEPTION_ID = "Trip with id 22222 not found.";
    private static final String EXPECTED_EXCEPTION_NAME = "Trip with name 'Non existing trip name' not found.";
    private static final String EXPECTED_EXCEPTION_REQUIRED_NAME_IS_MISSING = "Required parameter 'name' is missing.";
    private static final String EXPECTED_EXCEPTION_NOTHING_TO_DELETE = "Object id is null. Nothing to delete.";
    private static final String EXPECTED_EXCEPTION_DUPLICATE_ENTITY = "Trip with name: 'Second test trip' already exists.";
    private static final String NEW_TRIP_NAME = "New trip name";

    @Autowired
    private TripService tripService;

    @MockBean
    private TripRepository tripRepository;

    @BeforeEach
    public void before() {
        Trip firstTrip = createTrip(TRIP_ID_1, EXPECTED_FIRST_TRIP_NAME);
        Trip secondTrip = createTrip(TRIP_ID_2, EXPECTED_SECOND_TRIP_NAME);
        when(tripRepository.findById(TRIP_ID_1)).thenReturn(Optional.of(firstTrip));
        when(tripRepository.findById(TRIP_ID_2)).thenReturn(Optional.of(secondTrip));
        when(tripRepository.findByName(EXPECTED_FIRST_TRIP_NAME)).thenReturn(Optional.of(firstTrip));
        when(tripRepository.findByName(EXPECTED_SECOND_TRIP_NAME)).thenReturn(Optional.of(secondTrip));
        when(tripRepository.findAll()).thenReturn(List.of(firstTrip, secondTrip));
        when(tripRepository.existsById(TRIP_ID_2)).thenReturn(true);
    }

    @Test
    public void getTripByIdExisting() {
        Trip trip = tripService.getTripById(TRIP_ID_1);

        assertEquals(trip.getId(), 1);
        assertEquals(trip.getName(), EXPECTED_FIRST_TRIP_NAME);
    }

    @Test
    public void getTripByIdNonExisting() {
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            tripService.getTripById(NON_EXISTING_TRIP_ID);
        });

        assertEquals(EXPECTED_EXCEPTION_ID, exception.getMessage());
    }

    @Test
    public void getTripByNameExisting() {
        Trip trip = tripService.getTripByName(EXPECTED_FIRST_TRIP_NAME);

        assertEquals(trip.getId(), 1);
        assertEquals(trip.getName(), EXPECTED_FIRST_TRIP_NAME);
    }

    @Test
    public void getTripByNameNonExisting() {
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            tripService.getTripByName(NON_EXISTING_TRIP_NAME);
        });

        assertEquals(EXPECTED_EXCEPTION_NAME, exception.getMessage());
    }

    @Test
    public void getAllTrips() {
        List<Trip> trips = tripService.getAllTrips();

        assertEquals(trips.size(), 2);
        assertEquals(trips.get(0).getId(), 1L);
        assertEquals(trips.get(0).getName(), EXPECTED_FIRST_TRIP_NAME);
        assertEquals(trips.get(1).getId(), 2L);
        assertEquals(trips.get(1).getName(), EXPECTED_SECOND_TRIP_NAME);
    }

    @Test
    public void saveTrip() {
        Trip trip = createTrip(3L, NEW_TRIP_NAME);
        tripService.saveTrip(trip);

        verify(tripRepository, times(1)).save(trip);
    }

    @Test
    public void saveTripEmptyName() {
        Trip trip = createTrip(3L, "");

        InvalidInputParametersException exception = assertThrows(InvalidInputParametersException.class, () -> {
            tripService.saveTrip(trip);
        });

        assertEquals(EXPECTED_EXCEPTION_REQUIRED_NAME_IS_MISSING, exception.getMessage());
    }

    @Test
    public void saveTripExisting() {
        Trip trip = createTrip(2L, EXPECTED_SECOND_TRIP_NAME);

        DuplicateEntityException exception = assertThrows(DuplicateEntityException.class, () -> {
            tripService.saveTrip(trip);
        });

        assertEquals(EXPECTED_EXCEPTION_DUPLICATE_ENTITY, exception.getMessage());
    }

    @Test
    public void deleteTrip() {
        tripService.deleteTrip(TRIP_ID_2);

        verify(tripRepository, times(1)).deleteById(TRIP_ID_2);
    }

    @Test
    public void deleteTripNullId() {
        InvalidInputParametersException exception = assertThrows(InvalidInputParametersException.class, () -> {
            tripService.deleteTrip(null);
        });

        assertEquals(EXPECTED_EXCEPTION_NOTHING_TO_DELETE, exception.getMessage());
    }

    @Test
    public void deleteTripNonExisting() {
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            tripService.deleteTrip(NON_EXISTING_TRIP_ID);
        });

        assertEquals(EXPECTED_EXCEPTION_ID, exception.getMessage());
    }

    private Trip createTrip(long id, String name) {
        Trip trip = new Trip();
        trip.setId(id);
        trip.setName(name);

        return trip;
    }
}
