package com.viniciusps2.flightApi.domain.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import static com.viniciusps2.flightApi.domain.flight.FlightSpecifications.*;
import static org.springframework.data.jpa.domain.Specifications.where;


@Service
public class FlightSearchService {

    @Autowired
    private FlightRepository flightRepository;

    public Page<Flight> search (FlightSearchDTO searchDTO, Pageable pageRequest) {
        return flightRepository.findAll(advancedSearch(searchDTO), pageRequest);
    }

    public Flight findById (Long id) {
        return flightRepository.findOne(where(whenId(id)).and(joinFetchAll()));
    }

    public Specifications<Flight> advancedSearch(FlightSearchDTO search) {
        return
                where(whenId(search.getId()))
                .and(whenPilotId(search.getPilotId()))
                .and(whenAirlineId(search.getAirlineId()))
                .and(whenAircraftId(search.getAircraftId()))
                .and(whenDeparture(search.getDeparture()))
                .and(whenArrival(search.getArrival()))
                .and(whenDestination(search.getDestinationId()))
                .and(whenOrigin(search.getOriginId()))
                .and(whenStatus(search.getStatus()))
                .and(joinFetchAll());
    }

}
