package com.viniciusps2.flightApi.flight;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.viniciusps2.flightApi.common.QueryPredicates.isEqualWhenExists;
import static com.viniciusps2.flightApi.common.QueryPredicates.matchDateRange;


@Service
public class FlightSearchService {

    @Autowired
    private FlightRepository flightRepository;

    public Page<Flight> search (FlightSearchDTO searchDTO, Pageable pageRequest) {
        return flightRepository.findAll(getSearchQuery(searchDTO), pageRequest);
    }

    private Predicate getSearchQuery(FlightSearchDTO searchDTO) {
        QFlight flight = QFlight.flight;
        return new BooleanBuilder()
            .and(matchDateRange(flight.departureDate, searchDTO.getDeparture()))
            .and(matchDateRange(flight.arrivalDate, searchDTO.getArrival()))
            .and(isEqualWhenExists(flight.aircraft.id, searchDTO.getAircraftId()))
            .and(isEqualWhenExists(flight.pilot.id, searchDTO.getAircraftId()))
            .and(isEqualWhenExists(flight.status, searchDTO.getStatus()))
            .and(isEqualWhenExists(flight.id, searchDTO.getId()))
            .getValue();
    }

}
