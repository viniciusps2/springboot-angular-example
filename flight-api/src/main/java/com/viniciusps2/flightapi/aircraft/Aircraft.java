package com.viniciusps2.flightapi.aircraft;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Aircraft {

    @Id
    private Long id;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String serialNumber;
}
