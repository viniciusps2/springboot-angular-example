package com.viniciusps2.flightApi.domain.flight;

import com.viniciusps2.flightApi.common.DateRange;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightSearchDTO {
    private Long id;
    private DateRange departure;
    private DateRange arrival;
    private Long originId;
    private Long destinationId;
    private Long aircraftId;
    private Long pilotId;
    private Long airlineId;
    private FlightStatus status;
}
