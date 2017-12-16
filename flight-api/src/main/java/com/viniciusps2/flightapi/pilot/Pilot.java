package com.viniciusps2.flightapi.pilot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pilot {
    @Id
    private Long id;

    private String firstName;
    private String lastName;
}
