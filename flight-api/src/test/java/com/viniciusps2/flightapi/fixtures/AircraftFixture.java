package com.viniciusps2.flightapi.fixtures;

import com.viniciusps2.flightapi.aircraft.Aircraft;

public class AircraftFixture {
    public static Aircraft getAircraft () {
        return new Aircraft(1L,"Phenom 600","1234");
    }
}
