package com.viniciusps2.flightApi.web.api;

import com.viniciusps2.flightApi.FlightApplication;
import com.viniciusps2.flightApi.domain.aircraft.Aircraft;
import com.viniciusps2.flightApi.domain.aircraft.AircraftRepository;
import com.viniciusps2.flightApi.fixtures.AircraftFixtureBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=FlightApplication.class)
@AutoConfigureMockMvc
public class AircraftControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AircraftRepository aircraftRepository;

    @Test
    public void whenListThenReturnAircrafts () throws Exception {
        List<Aircraft> aircrafts = Arrays.asList(
                AircraftFixtureBuilder.getAircraft().build(),
                AircraftFixtureBuilder.getAircraft().build());

        given(aircraftRepository.findAll()).willReturn(aircrafts);

        mvc.perform(get("/aircrafts").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
