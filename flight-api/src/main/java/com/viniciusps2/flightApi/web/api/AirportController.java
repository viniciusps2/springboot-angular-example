package com.viniciusps2.flightApi.web.api;

import com.viniciusps2.flightApi.domain.airport.Airport;
import com.viniciusps2.flightApi.domain.airport.AirportRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/airports")
@Api("Airport")
public class AirportController {

    @Autowired
    private AirportRepository airportRepository;

    @GetMapping
    @ApiOperation("List all airports")
    public List<Airport> list() {
        return airportRepository.findAll();
    }
}
