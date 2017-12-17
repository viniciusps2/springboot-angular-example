package com.viniciusps2.flightApi.domain.flight;

import com.viniciusps2.flightApi.common.DateRange;
import lombok.Data;

@Data
public class FlightSearchDTO {
    private Long id;
    private DateRange departure;
    private DateRange arrival;
    private Long originId;
    private Long destinationId;
    private FlightStatus status;
    private Long aircraftId;
    private Long pilotId;
}
