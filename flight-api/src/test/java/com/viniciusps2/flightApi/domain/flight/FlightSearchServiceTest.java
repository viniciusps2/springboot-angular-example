package com.viniciusps2.flightApi.domain.flight;

import com.viniciusps2.flightApi.FlightApplication;
import com.viniciusps2.flightApi.fixtures.FlightFixture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=FlightApplication.class)
@DataJpaTest
public class FlightSearchServiceTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FlightSearchService flightSearchService;

//    @Autowired
//    private FlightRepository flightRepository;


    @Test
    public void whenSearchThenReturnFlights(){
        Flight flight = FlightFixture.builder().build();
        entityManager.persist(flight.getAircraft());
        entityManager.persist(flight.getPilot());
        entityManager.persist(flight.getOrigin());
        entityManager.persist(flight.getDestination());
        entityManager.persist(flight.getAirline());
        entityManager.persist(flight);
        entityManager.flush();

//        FlightSearchDTO searchParams = FlightSearchDTO.builder()
//                .status(FlightStatus.SCHEDULED)
//                .build();
//
//        Page<Flight> found = flightSearchService
//                .search(searchParams, new PageRequest(1, 5));
//
//        assertThat(found.getContent().get(1).getStatus(), is(FlightStatus.SCHEDULED));
    }

}
