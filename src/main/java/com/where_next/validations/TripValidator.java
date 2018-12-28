package com.where_next.validations;

import com.where_next.entities.Trip;
import com.where_next.exceptions.DuplicateEntityException;
import com.where_next.exceptions.EntityNotFoundException;
import com.where_next.exceptions.InvalidInputParametersException;
import com.where_next.repositories.TripRepository;
import com.where_next.utils.BaseUtil;
import org.springframework.util.StringUtils;

import java.util.Optional;

public class TripValidator implements BaseValidator<Trip>, RemovalValidator {

    private static final String TYPE_CLASS = BaseUtil.getGenericType(TripValidator.class).getSimpleName();

    private TripRepository tripRepository;

    public TripValidator(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public void validate(Long id) {
        if (id == null) {
            throw new InvalidInputParametersException(NOTHING_TO_DELETE_ERROR_MSG);
        }

        if (!tripRepository.existsById(id)) {
            throw new EntityNotFoundException(ENTITY_WITH_ID_NOT_FOUND_ERROR_MSG, TYPE_CLASS, id);
        }
    }

    @Override
    public void validate(Trip trip) {
        if (StringUtils.isEmpty(trip.getName())) {
            throw new InvalidInputParametersException(REQUIRED_PARAMETER_NAME_MISSING_ERROR_MSG);
        }

        Optional<Trip> existingTrip = tripRepository.findByName(trip.getName());
        if (existingTrip.filter(t -> t.getId() == trip.getId()).isPresent()) {
            throw new DuplicateEntityException(ENTITY_WITH_NAME_EXISTS_ERROR_MSG, TYPE_CLASS, trip.getName());
        }
    }

    public String type() {
        return TYPE_CLASS;
    }
}
