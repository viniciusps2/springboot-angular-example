package com.viniciusps2.flightApi.web.api;

import com.viniciusps2.flightApi.FlightApplication;
import com.viniciusps2.flightApi.domain.flight.Flight;
import com.viniciusps2.flightApi.domain.flight.FlightSearchDTO;
import com.viniciusps2.flightApi.domain.flight.FlightSearchService;
import com.viniciusps2.flightApi.fixtures.FlightFixtureBuilder;
import com.viniciusps2.flightApi.helpers.PageWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=FlightApplication.class)
@AutoConfigureMockMvc
@WebAppConfiguration
public class FlightControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FlightSearchService flightSearchService;

    @Test
    public void givenFlightsWhenGetListThenReturnResults() throws Exception {
        List<Flight> flights = Arrays.asList(FlightFixtureBuilder.getFlight().id(1L).build());
        Page<Flight> flightPage = new PageWrapper<>(flights).getPage();

        given(flightSearchService
                .search(any(FlightSearchDTO.class), any(Pageable.class)))
                .willReturn(flightPage);

        mvc.perform(get("/flights").contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content", hasSize(1)));
    }

    @Test
    public void givenFlightWhenFindOneThenReturnOne() throws Exception {
        Flight flight = FlightFixtureBuilder.getFlight().id(1L).build();
        given(flightSearchService.findById(flight.getId()))
                .willReturn(flight);

        mvc.perform(get("/flights/" + flight.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(flight.getId().intValue())));

    }

    @Test
    public void whenNoFlightWhenFindOneThenReturnNotFound() throws Exception {
        Flight flight = FlightFixtureBuilder.getFlight().id(1L).build();
        given(flightSearchService.findById(flight.getId()))
                .willReturn(null);

        mvc.perform(get("/flights/" + flight.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }


}
