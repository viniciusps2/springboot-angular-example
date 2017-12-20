package com.viniciusps2.flightApi.web.api;

import com.viniciusps2.flightApi.domain.pilot.Pilot;
import com.viniciusps2.flightApi.domain.pilot.PilotRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pilots")
@Api("Pilot")
public class PilotController {

    @Autowired
    private PilotRepository pilotRepository;

    @GetMapping
    @ApiOperation("List all pilots")
    public List<Pilot> list() {
        return pilotRepository.findAll();
    }
}
