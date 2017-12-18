package com.viniciusps2.flightApi.domain.flight;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import static com.viniciusps2.flightApi.common.QueryPredicates.isEqualWhenExists;
import static com.viniciusps2.flightApi.common.QueryPredicates.matchDateRange;
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
                .and(joinFetchAll());
    }

    private Predicate getSearchQuery(FlightSearchDTO searchDTO) {
        QFlight flight = QFlight.flight;

        if (searchDTO.getId() != null) {
            return flight.id.eq(searchDTO.getId());
        }

        return new BooleanBuilder()
            .and(matchDateRange(flight.departureDate, searchDTO.getDeparture()))
            .and(matchDateRange(flight.arrivalDate, searchDTO.getArrival()))
            .and(isEqualWhenExists(flight.aircraft.id, searchDTO.getAircraftId()))
            .and(isEqualWhenExists(flight.pilot.id, searchDTO.getPilotId()))
            .and(isEqualWhenExists(flight.status, searchDTO.getStatus()));
    }

}
