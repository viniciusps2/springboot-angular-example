package com.viniciusps2.flightApi.web;

import com.viniciusps2.flightApi.domain.aircraft.Aircraft;
import com.viniciusps2.flightApi.domain.aircraft.AircraftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/aircrafts")
public class AircraftController {

    @Autowired
    private AircraftRepository aircraftRepository;

    @GetMapping
    public List<Aircraft> list() {
        return aircraftRepository.findAll();
    }
}
