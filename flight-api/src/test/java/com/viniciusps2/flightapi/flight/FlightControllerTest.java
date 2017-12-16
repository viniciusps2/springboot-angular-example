package com.viniciusps2.flightapi.flight;

import com.viniciusps2.flightapi.FlightApplication;
import com.viniciusps2.flightapi.fixtures.FlightFixture;
import com.viniciusps2.flightapi.helpers.PageWrapper;
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
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=FlightApplication.class)
@AutoConfigureMockMvc
public class FlightControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FlightRepository flightRepository;

    @Test
    public void givenFlightsWhenGetListThenReturnResults() throws Exception {
        List<Flight> flights = Arrays.asList(FlightFixture.getScheduledFlight());
        Page<Flight> flightPage = new PageWrapper<>(flights).getPage();

        given(flightRepository.findAll(any(Pageable.class))).willReturn(flightPage);

        mvc.perform(get("/flights").contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content", hasSize(1)));
    }

}
