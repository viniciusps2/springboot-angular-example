package com.viniciusps2.flightApi.web.api;

import com.viniciusps2.flightApi.domain.airport.Airport;
import com.viniciusps2.flightApi.domain.airport.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/airports")
public class AirportController {

    @Autowired
    private AirportRepository airportRepository;

    @GetMapping
    public List<Airport> list() {
        return airportRepository.findAll();
    }
}
