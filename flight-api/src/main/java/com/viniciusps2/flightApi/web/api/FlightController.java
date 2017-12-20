package com.viniciusps2.flightApi.web.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viniciusps2.flightApi.domain.flight.Flight;
import com.viniciusps2.flightApi.domain.flight.FlightSearchDTO;
import com.viniciusps2.flightApi.domain.flight.FlightSearchService;
import com.viniciusps2.flightApi.web.errors.BadRequestException;
import com.viniciusps2.flightApi.web.errors.NotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/flights")
@Api("Flights")
public class FlightController {

    @Autowired
    private FlightSearchService flightSearchService;

    @GetMapping
    @ApiOperation(value = "Advanced Flight Search",
            notes = "Put the ?search=(strigified JSON with search criteria).\n\n" +
                    "Example: ?search={\"pilotId\":\"1\"}")
    public Page<Flight> search(@PageableDefault(sort = {"id"}) Pageable pageRequest,
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
    @ApiOperation("Find one flight by id")
    public Flight read(@PathVariable(value = "id") long id) {
        Flight flight = flightSearchService.findById(id);
        if (flight == null) throw new NotFoundException();
        return flight;
    }

}
