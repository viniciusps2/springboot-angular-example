package com.viniciusps2.flightapp.flight;

import com.viniciusps2.flightapp.aircraft.Aircraft;
import com.viniciusps2.flightapp.city.City;
import com.viniciusps2.flightapp.pilot.Pilot;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
public class Flight {

    @Id
    private Long id;

    private FlightStatus flightStatus;

    @OneToOne
    @JoinColumn(name = "aircraft_id", nullable = false)
    private Aircraft aircraft;

    @OneToOne
    @JoinColumn(name = "origin_city_id", nullable = false)
    private City originCity;

    @OneToOne
    @JoinColumn(name = "destination_city_id", nullable = false)
    private City destinationCity;

    @OneToOne
    @JoinColumn(name = "pilot_id", nullable = false)
    private Pilot pilot;

    @Column(nullable = false)
    private Date departureDate;

    @Column(nullable = false)
    private Date arrivalDate;

    public String getFlightStatus() {
        return flightStatus.getLabel();
    }
}
