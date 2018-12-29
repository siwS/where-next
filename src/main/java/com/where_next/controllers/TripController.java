package com.where_next.controllers;

import com.where_next.entities.Trip;
import com.where_next.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TripController {

    @Autowired
    private TripService tripService;


    @RequestMapping(value = "/trip", method = RequestMethod.GET, produces = "application/json")
    public Trip getTrip(@RequestParam(value = "id", required = false) Long id,
                        @RequestParam(value = "name", required = false) String name) {
        if (StringUtils.isEmpty(name)) {
            return tripService.getTripById(id);
        } else {
            return tripService.getTripByName(name);
        }
    }
}
