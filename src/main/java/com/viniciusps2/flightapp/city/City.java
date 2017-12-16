package com.viniciusps2.flightapp.city;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class City {
    @Id
    private Long id;

    private String name;
    private String stateCode;
}
