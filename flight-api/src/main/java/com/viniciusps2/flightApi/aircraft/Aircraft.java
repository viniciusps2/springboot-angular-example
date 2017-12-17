package com.viniciusps2.flightApi.aircraft;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Aircraft {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String serialNumber;
}
