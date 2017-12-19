package com.viniciusps2.flightApi.domain.flight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FlightRepository extends
        JpaRepository<Flight, Long>, JpaSpecificationExecutor<Flight>{

}
