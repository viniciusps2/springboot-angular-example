package com.viniciusps2.flightapp.aircraft;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Aircraft {

    @Id
    private Long id;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String serialNumber;
}
