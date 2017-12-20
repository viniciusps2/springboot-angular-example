package com.viniciusps2.flightApi.web.api;

import com.viniciusps2.flightApi.domain.airline.Airline;
import com.viniciusps2.flightApi.domain.airline.AirlineRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/airlines")
@Api("Airline")
public class AirlineController {

    @Autowired
    private AirlineRepository airlineRepository;

    @GetMapping
    @ApiOperation("List all airlines")
    public List<Airline> list() {
        return airlineRepository.findAll();
    }
}
