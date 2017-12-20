package com.viniciusps2.flightApi.web.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viniciusps2.flightApi.domain.flight.Flight;
import com.viniciusps2.flightApi.domain.flight.FlightSearchDTO;
import com.viniciusps2.flightApi.domain.flight.FlightSearchService;
import com.viniciusps2.flightApi.web.errors.BadRequestException;
import com.viniciusps2.flightApi.web.errors.NotFoundException;
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
                             @RequestParam(value = "search", required = false) String searchJson) throws BadRequestException {
        try {
            FlightSearchDTO searchDTO = searchJson == null
                    ? new FlightSearchDTO()
                    : new ObjectMapper().readValue(searchJson, FlightSearchDTO.class);
            return flightSearchService.search(searchDTO, pageRequest);
        } catch (IOException e) {
            throw new BadRequestException();
        }
    }

    @GetMapping("/{id}")
    public Flight read(@PathVariable(value = "id") long id) {
        Flight flight = flightSearchService.findById(id);

        if (flight == null) throw new NotFoundException();

        return flight;
    }

}
