package com.viniciusps2.flightapp.fixtures;

import com.viniciusps2.flightapp.city.City;
import com.viniciusps2.flightapp.flight.Flight;
import com.viniciusps2.flightapp.flight.FlightStatus;
import com.viniciusps2.flightapp.pilot.Pilot;

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
                .flightStatus(FlightStatus.SCHEDULED)
                .build();

    }
}
