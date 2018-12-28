package com.where_next.configuration;

import com.where_next.entities.Trip;
import com.where_next.repositories.TripRepository;
import com.where_next.validations.BaseValidator;
import com.where_next.validations.TripValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationConfig {

    @Bean
    @Autowired
    public BaseValidator<Trip> tripValidator(TripRepository tripRepository) {
        return new TripValidator(tripRepository);
    }
}
