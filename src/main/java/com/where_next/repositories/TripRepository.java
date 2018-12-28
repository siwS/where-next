package com.where_next.repositories;

import com.where_next.entities.Trip;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TripRepository extends CrudRepository<Trip, Long> {
    Optional<Trip> findByName(String name);
}
