package com.viniciusps2.flightApi.flight;

import com.viniciusps2.flightApi.aircraft.Aircraft;
import com.viniciusps2.flightApi.airport.Airport;
import com.viniciusps2.flightApi.pilot.Pilot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    @Id
    @GeneratedValue
    private Long id;

    private FlightStatus status;

    @OneToOne
    @JoinColumn(name = "aircraft_id", nullable = false)
    private Aircraft aircraft;

    @OneToOne
    @JoinColumn(name = "origin_id", nullable = false)
    private Airport origin;

    @OneToOne
    @JoinColumn(name = "destination_id")
    private Airport destination;

    @OneToOne
    @JoinColumn(name = "pilot_id")
    private Pilot pilot;

    @Column(nullable = false)
    private Date departureDate;

    @Column(nullable = false)
    private Date arrivalDate;

    public String getStatusLabel() {
        return status.getLabel();
    }
}
