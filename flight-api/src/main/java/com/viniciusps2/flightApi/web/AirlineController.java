package com.viniciusps2.flightApi.web;

import com.viniciusps2.flightApi.domain.airline.Airline;
import com.viniciusps2.flightApi.domain.airline.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/airlines")
public class AirlineController {

    @Autowired
    private AirlineRepository airlineRepository;

    @GetMapping
    public List<Airline> list() {
        return airlineRepository.findAll();
    }
}
