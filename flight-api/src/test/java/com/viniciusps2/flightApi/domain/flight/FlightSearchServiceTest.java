package com.viniciusps2.flightApi.domain.flight;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class FlightSearchServiceTest {

    @Autowired
    private FlightSearchService flightSearchService;

    @Test
    public void whenSearchThenReturnFlights(){
        FlightSearchDTO searchParams = FlightSearchDTO.builder()
                .status(FlightStatus.SCHEDULED)
                .build();

        Page<Flight> page = flightSearchService
                .search(searchParams, new PageRequest(0, 10));

        assertEquals(page.getTotalElements(), 1);
        assertEquals(page.getContent().get(0).getStatus(), FlightStatus.SCHEDULED);
    }

}
