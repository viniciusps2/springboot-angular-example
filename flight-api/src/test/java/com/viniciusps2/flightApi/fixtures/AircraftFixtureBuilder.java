package com.viniciusps2.flightApi.fixtures;

import com.viniciusps2.flightApi.domain.aircraft.Aircraft;

public class AircraftFixtureBuilder {
    public static Aircraft.AircraftBuilder getAircraft () {
        return Aircraft.builder()
                .id(1L)
                .model("Phenom 600")
                .serialNumber("1234");
    }
}
