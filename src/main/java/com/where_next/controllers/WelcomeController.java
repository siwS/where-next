package com.where_next.controllers;

import com.where_next.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class WelcomeController {

    @Autowired
    TripService tripService;

    @RequestMapping("/")
    public String welcome(Model model) {
        List<String> tripList = tripService.getAllTrips().stream().map(e -> e.getName()).collect(Collectors.toList());
        model.addAttribute("all_trips", tripList);
        return "welcome";
    }
}