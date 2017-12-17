package com.viniciusps2.flightApi.flight;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightController.class);

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
        FlightSearchDTO searchDTO = new ObjectMapper().readValue(searchJson, FlightSearchDTO.class);
        LOGGER.info(searchDTO.toString());

        return flightSearchService.search(searchDTO, pageRequest);
    }

    @GetMapping(value = "/{id}")
    public Flight read(@PathVariable(value = "id") long id) {
        return flightRepository.findOne(id);
    }

    @PutMapping(value = "/{id}")
    public Flight update(@PathVariable(value = "id") long id, @RequestBody Flight flight) {
        return flightRepository.save(flight);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(value = "id") long id) {
        flightRepository.delete(id);
    }
}
