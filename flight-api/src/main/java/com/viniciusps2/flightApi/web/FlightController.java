package com.viniciusps2.flightApi.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viniciusps2.flightApi.domain.flight.Flight;
import com.viniciusps2.flightApi.domain.flight.FlightSearchDTO;
import com.viniciusps2.flightApi.domain.flight.FlightSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightSearchService flightSearchService;

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
