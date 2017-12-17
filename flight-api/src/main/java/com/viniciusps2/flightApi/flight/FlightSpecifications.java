package com.viniciusps2.flightApi.flight;

import com.viniciusps2.flightApi.common.DateRange;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;


public class FlightSpecifications {
    private FlightSpecifications () {}

    public static Specifications<Flight> advancedSearch (FlightSearchDTO search) {
        return Specifications
            .where(isEquals("id", search.getId()))
            .and(isEquals("pilot.id", search.getPilotId()))
            .and(matchDateRange("departureDate", search.getDeparture()))
            .and(matchDateRange("arrivalDate", search.getArrival()));
    }

    private static <T> Specification<Flight> isEquals (String attribute, T value) {
        return (root, query, cb) ->
                value == null ? null : cb.equal(root.get(attribute), value);
    }
    private static Specification<Flight> matchDateRange (String attribute, DateRange dateRange) {
        return (root, query, cb) -> {
            if (dateRange == null) return null;
            List<Predicate> predicates = new ArrayList<>();

            if (dateRange.getFrom() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get(attribute), dateRange.getFrom()));
            }
            if (dateRange.getUntil() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get(attribute), dateRange.getUntil()));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
