package com.viniciusps2.flightApi.flight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface FlightRepository extends
        JpaRepository<Flight, Long>, JpaSpecificationExecutor<Flight>,
        QueryDslPredicateExecutor<Flight> {

}
