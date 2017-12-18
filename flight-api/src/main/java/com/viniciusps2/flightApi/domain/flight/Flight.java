package com.viniciusps2.flightApi.domain.flight;

import com.viniciusps2.flightApi.domain.aircraft.Aircraft;
import com.viniciusps2.flightApi.domain.airport.Airport;
import com.viniciusps2.flightApi.domain.pilot.Pilot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.jdbc.core.CallableStatementCallback;

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
    // todo: retirar set id
    private Long id;

    private FlightStatus status;

    // todo: empresa aérea
    // todo: lazy
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "aircraft_id", nullable = false)
    private Aircraft aircraft;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "origin_id", nullable = false)
    private Airport origin;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "destination_id")
    private Airport destination;

    @ManyToOne(cascade = CascadeType.ALL)
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
