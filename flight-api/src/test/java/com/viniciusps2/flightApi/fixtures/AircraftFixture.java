package com.viniciusps2.flightApi.fixtures;

import com.viniciusps2.flightApi.domain.aircraft.Aircraft;

public class AircraftFixture {
    public static Aircraft getAircraft () {
        return new Aircraft(null,"Phenom 600","1234");
    }
}
