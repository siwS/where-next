package com.where_next.repositories;

import java.util.List;

import com.where_next.entities.Trip;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends CrudRepository<Trip, Long> {
    List<Trip> findById(long id);
}
