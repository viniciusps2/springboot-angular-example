package com.viniciusps2.flightApi.domain.flight;

import com.viniciusps2.flightApi.common.DateRange;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class FlightSearchServiceTest {

    @Autowired
    private FlightSearchService flightSearchService;


    @Test
    public void whenSearchThenReturnFlights(){
        FlightSearchDTO searchParams = queryToGetTwoFlights();
        Page<Flight> page = flightSearchService
                .search(searchParams, new PageRequest(0, 10));

        assertEquals(2, page.getTotalElements());
        assertEquals(FlightStatus.IN_AIR, page.getContent().get(0).getStatus());
        assertEquals(FlightStatus.IN_AIR, page.getContent().get(1).getStatus());
    }

    @Test
    public void givenNoFlightFoundWhenSearchThenReturnAPageWithNoFlights(){
        FlightSearchDTO searchParams = queryToGetTwoFlights();
        searchParams.setAircraftId(50L);

        Page<Flight> page = flightSearchService
                .search(searchParams, new PageRequest(0, 10));

        assertEquals(0, page.getTotalElements());
    }

    @Test
    public void whenSearchByDateRangeThenReturnOneFlight(){
        Date fromDate = new GregorianCalendar(2017, 02, 03).getTime();
        FlightSearchDTO searchParams = queryToGetTwoFlights();
        searchParams.setDeparture(new DateRange(fromDate, null));

        Page<Flight> page = flightSearchService
                .search(searchParams, new PageRequest(0, 10));

        assertEquals(1, page.getTotalElements());
        assertEquals(4, page.getContent().get(0).getId().intValue());
    }

    public FlightSearchDTO queryToGetTwoFlights() {
        return FlightSearchDTO.builder()
                .pilotId(2L)
                .airlineId(2L)
                .aircraftId(2L)
                .aircraftId(2L)
                .originId(2L)
                .destinationId(1L)
                .status(FlightStatus.IN_AIR)
                .departure(new DateRange(
                        new GregorianCalendar(2017,01,01).getTime(),
                        new GregorianCalendar(2017,12,31).getTime()))
                .build();
    }
}
