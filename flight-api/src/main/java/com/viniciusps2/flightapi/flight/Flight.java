package com.viniciusps2.flightapi.flight;

import com.viniciusps2.flightapi.aircraft.Aircraft;
import com.viniciusps2.flightapi.city.City;
import com.viniciusps2.flightapi.pilot.Pilot;
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
    private Long id;

    private FlightStatus status;

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

    public String getStatusLabel() {
        return status.getLabel();
    }
}
