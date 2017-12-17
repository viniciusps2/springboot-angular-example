package com.viniciusps2.flightApi.fixtures;

import com.viniciusps2.flightApi.aircraft.Aircraft;

public class AircraftFixture {
    public static Aircraft getAircraft () {
        return new Aircraft(1L,"Phenom 600","1234");
    }
}
