package com.viniciusps2.flightapi.fixtures;

import com.viniciusps2.flightapi.city.City;
import com.viniciusps2.flightapi.flight.Flight;
import com.viniciusps2.flightapi.flight.FlightStatus;
import com.viniciusps2.flightapi.pilot.Pilot;

import java.util.Date;

public class FlightFixture {
    public static Flight getScheduledFlight() {
        return Flight.builder()
                .id(1L)
                .arrivalDate(new Date())
                .departureDate(new Date())
                .aircraft(AircraftFixture.getAircraft())
                .destinationCity(new City(1L, "Guarulhos", "SP", "BR"))
                .originCity(new City(2L, "Congonhas", "SP", "BR"))
                .pilot(new Pilot(1L, "Fulano", "Silva"))
                .status(FlightStatus.SCHEDULED)
                .build();

    }
}
