package com.viniciusps2.flightApi.domain.flight;

import com.viniciusps2.flightApi.common.DateRange;
import com.viniciusps2.flightApi.domain.aircraft.Aircraft_;
import com.viniciusps2.flightApi.domain.airline.Airline_;
import com.viniciusps2.flightApi.domain.airport.Airport_;
import com.viniciusps2.flightApi.domain.pilot.Pilot_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.metamodel.SingularAttribute;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlightSpecifications {
    private FlightSpecifications () {}

    public static Specification<Flight> whenId(Long value) {
        return (root, query, cb) ->
            value == null ? null : cb.equal(root.get(Flight_.id), value);
    }

    public static Specification<Flight> whenAirlineId(Long value) {
        return (root, query, cb) ->
            value == null ? null : cb.equal(root.get(Flight_.airline).get(Airline_.id), value);
    }

    public static Specification<Flight> whenPilotId(Long value) {
        return (root, query, cb) ->
            value == null ? null : cb.equal(root.get(Flight_.pilot).get(Pilot_.id), value);
    }

    public static Specification<Flight> whenAircraftId(Long value) {
        return (root, query, cb) ->
            value == null ? null : cb.equal(root.get(Flight_.aircraft).get(Aircraft_.id), value);
    }

    public static Specification<Flight> whenOrigin(Long value) {
        return (root, query, cb) ->
            value == null ? null : cb.equal(root.get(Flight_.origin).get(Airport_.id), value);
    }

    public static Specification<Flight> whenDestination(Long value) {
        return (root, query, cb) ->
            value == null ? null : cb.equal(root.get(Flight_.destination).get(Airport_.id), value);
    }

    public static Specification<Flight> whenStatus(FlightStatus status) {
        return (root, query, cb) ->
                status == null ? null : cb.equal(root.get(Flight_.status), status);
    }

    public static Specification<Flight> whenDeparture(DateRange dateRange) {
        return matchDateRange(Flight_.departureDate, dateRange);
    }

    public static Specification<Flight> whenArrival(DateRange dateRange) {
        return matchDateRange(Flight_.departureDate, dateRange);
    }

    public static Specification<Flight> matchDateRange (SingularAttribute<Flight, Date> attribute, DateRange dateRange) {
        return (root, query, cb) -> {
            if (dateRange == null) {
                return null;
            }

            List<Predicate> predicates = new ArrayList<>();

            if (dateRange.getFrom() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get(attribute), dateRange.getFrom()));
            }
            if (dateRange.getUntil() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get(attribute), dateRange.getUntil()));
            }

            return cb.and(predicates.toArray(new Predicate[]{}));
        };
    }

    public static Specification<Flight> joinFetchAll() {
        return  (root, query, cb) -> {
            Boolean isCountQuery = query.getResultType().equals(Long.class);
            if (!isCountQuery) {
                root.fetch("aircraft", JoinType.LEFT);
                root.fetch("airline", JoinType.LEFT);
                root.fetch("origin", JoinType.LEFT);
                root.fetch("destination", JoinType.LEFT);
                root.fetch("pilot", JoinType.LEFT);
            }
            return null;
        };
    }
    
}
