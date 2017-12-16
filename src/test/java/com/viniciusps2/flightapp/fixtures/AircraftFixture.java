package com.viniciusps2.flightapp.fixtures;

import com.viniciusps2.flightapp.aircraft.Aircraft;

public class AircraftFixture {
    public static Aircraft getAircraft () {
        return new Aircraft(1L,"Phenom 600","1234");
    }
}
