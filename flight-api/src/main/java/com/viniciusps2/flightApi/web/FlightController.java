package com.viniciusps2.flightApi.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viniciusps2.flightApi.domain.flight.Flight;
import com.viniciusps2.flightApi.domain.flight.FlightRepository;
import com.viniciusps2.flightApi.domain.flight.FlightSearchDTO;
import com.viniciusps2.flightApi.domain.flight.FlightSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private FlightSearchService flightSearchService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "")
    public Flight save(@RequestBody Flight flight) {
        return flightRepository.save(flight);
    }

    @GetMapping
    public Page<Flight> list(@PageableDefault(sort = {"id"}) Pageable pageRequest,
                             @RequestParam(value = "search", required = false) String searchJson) throws IOException {
        FlightSearchDTO searchDTO = searchJson == null
                ? new FlightSearchDTO()
                : new ObjectMapper().readValue(searchJson, FlightSearchDTO.class);

        return flightSearchService.search(searchDTO, pageRequest);
    }

    @GetMapping("/{id}")
    public Flight read(@PathVariable(value = "id") long id) {
        return flightSearchService.findById(id);
    }

}
