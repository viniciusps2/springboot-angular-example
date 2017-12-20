package com.viniciusps2.flightApi.fixtures;

import com.viniciusps2.flightApi.domain.aircraft.Aircraft;
import com.viniciusps2.flightApi.domain.airline.Airline;
import com.viniciusps2.flightApi.domain.airport.Airport;
import com.viniciusps2.flightApi.domain.flight.Flight;
import com.viniciusps2.flightApi.domain.flight.FlightStatus;
import com.viniciusps2.flightApi.domain.pilot.Pilot;

import java.util.Date;

public class FlightFixtureBuilder {
    public static Flight.FlightBuilder getFlight() {
        return Flight.builder()
                .arrivalDate(new Date())
                .departureDate(new Date())
                .aircraft(new Aircraft(1L,"Phenom 600","1234"))
                .airline(new Airline(1L, "TAM"))
                .destination(new Airport(1L, "Guarulhos, São Paulo", "Brazil", "GRU"))
                .origin(new Airport(2L, "Ôta, Tokyo", "Japan", "HND"))
                .pilot(new Pilot(1L, "Fulano", "Silva"))
                .status(FlightStatus.SCHEDULED);

    }
}
