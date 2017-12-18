package com.viniciusps2.flightApi.domain.flight;

import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import static com.viniciusps2.flightApi.common.QueryPredicates.isEqualWhenExists;
import static com.viniciusps2.flightApi.common.QueryPredicates.matchDateRange;
import static com.viniciusps2.flightApi.domain.flight.FlightSpecifications.*;


@Service
public class FlightSearchService {

    @Autowired
    private FlightRepository flightRepository;

    public Page<Flight> search (FlightSearchDTO searchDTO, Pageable pageRequest) {

        //Specification<Flight>specSearch = new FlightSpecifications<Flight>().advancedSearch(searchDTO);

        //Specifications<Flight> spec = Specifications.where(specFetch).and(getSearchQuery(searchDTO));
        //return flightRepository.findAll(getSearchQuery(searchDTO), pageRequest);
        //return flightRepository.findAll(spec, pageRequest);

        return flightRepository.findAll(advancedSearch(searchDTO), pageRequest);
    }

    public Specifications<Flight> advancedSearch(FlightSearchDTO search) {
        Specification<Flight> fetchAllSpec = (root, query, cb) -> {
            root.fetch("aircraft", JoinType.LEFT);
            root.fetch("origin", JoinType.LEFT);
            root.fetch("pilot", JoinType.LEFT);
            root.fetch("destination", JoinType.LEFT);
            return null;
        };

        return Specifications
                .where(id(search.getId()))
                .and(pilotId(search.getPilotId()))
                .and(aircraftId(search.getAircraftId()))
                .and(departure(search.getDeparture()))
                .and(arrival(search.getArrival()))
                .and(destination(search.getDestinationId()))
                .and(origin(search.getOriginId()))
                .and(fetchAllSpec);
    }

    private Predicate getSearchQuery(FlightSearchDTO searchDTO) {
        QFlight flight = QFlight.flight;

        if (searchDTO.getId() != null) {
            return (Predicate) flight.id.eq(searchDTO.getId());
        }

        return (Predicate) new BooleanBuilder()
            .and(matchDateRange(flight.departureDate, searchDTO.getDeparture()))
            .and(matchDateRange(flight.arrivalDate, searchDTO.getArrival()))
            .and(isEqualWhenExists(flight.aircraft.id, searchDTO.getAircraftId()))
            .and(isEqualWhenExists(flight.pilot.id, searchDTO.getPilotId()))
            .and(isEqualWhenExists(flight.status, searchDTO.getStatus()))
            .getValue();
    }

}
