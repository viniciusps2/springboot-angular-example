package com.viniciusps2.flightApi.common;

import lombok.Data;

import java.util.Date;

@Data
public class DateRange {
    private Date from;
    private Date until;
}
