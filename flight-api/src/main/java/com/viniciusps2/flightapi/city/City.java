package com.viniciusps2.flightapi.city;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {
    @Id
    private Long id;

    private String name;
    private String stateCode;
    private String countryCode;
}
