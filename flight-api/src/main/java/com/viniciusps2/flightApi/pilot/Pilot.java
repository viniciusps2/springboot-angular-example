package com.viniciusps2.flightApi.pilot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pilot {
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;

    public String getFullName () {
    	return firstName + " " + lastName;
    }
}
