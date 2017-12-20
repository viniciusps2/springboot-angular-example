package com.viniciusps2.flightApi.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class DateRange {
    private Date from;
    private Date until;
}
