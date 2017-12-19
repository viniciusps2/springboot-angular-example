package com.viniciusps2.flightApi.fixtures;

import com.viniciusps2.flightApi.domain.airline.Airline;
import com.viniciusps2.flightApi.domain.airport.Airport;
import com.viniciusps2.flightApi.domain.flight.Flight;
import com.viniciusps2.flightApi.domain.flight.FlightStatus;
import com.viniciusps2.flightApi.domain.pilot.Pilot;

import java.util.Date;

public class FlightFixture {
    public static Flight.FlightBuilder builder() {
        return Flight.builder()
                .arrivalDate(new Date())
                .departureDate(new Date())
                .aircraft(AircraftFixture.getAircraft())
                .airline(new Airline(null, "TAM"))
                .destination(new Airport(null, "Guarulhos, São Paulo", "Brazil", "GRU"))
                .origin(new Airport(null, "Ôta, Tokyo", "Japan", "HND"))
                .pilot(new Pilot(null, "Fulano", "Silva"))
                .status(FlightStatus.SCHEDULED);

    }
}
