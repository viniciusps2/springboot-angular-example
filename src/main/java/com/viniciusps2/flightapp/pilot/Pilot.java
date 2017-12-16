package com.viniciusps2.flightapp.pilot;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
public class Pilot {
    @Id
    private Long id;

    private String firstName;
    private String lastName;
}
