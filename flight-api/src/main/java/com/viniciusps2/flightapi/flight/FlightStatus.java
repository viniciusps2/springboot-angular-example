package com.viniciusps2.flightapi.flight;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FlightStatus {
    SCHEDULED("Scheduled"),
    DELAYED("Delayed"),
    DEPARTED("Departed"),
    IN_AIR("In Air"),
    EXPECTED("Expected"),
    ARRIVED("Arrived"),
    CANCELLED("Cancelled"),
    NO_INFO("No Info"),
    PAST_FLIGHT("Past Flight");

    private final String label;
}
