package com.viniciusps2.flightApi.fixtures;

import com.viniciusps2.flightApi.domain.airport.Airport;
import com.viniciusps2.flightApi.domain.flight.Flight;
import com.viniciusps2.flightApi.domain.flight.FlightStatus;
import com.viniciusps2.flightApi.domain.pilot.Pilot;

import java.util.Date;

public class FlightFixture {
    public static Flight getScheduledFlight() {
        return Flight.builder()
                .id(1L)
                .arrivalDate(new Date())
                .departureDate(new Date())
                .aircraft(AircraftFixture.getAircraft())
                .destination(new Airport(1L, "Guarulhos, São Paulo", "Brazil", "GRU"))
                .origin(new Airport(2L, "Ôta, Tokyo", "Japan", "HND"))
                .pilot(new Pilot(1L, "Fulano", "Silva"))
                .status(FlightStatus.SCHEDULED)
                .build();

    }
}
