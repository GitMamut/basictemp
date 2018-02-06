package com.mintfrost.sensor.temperature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TemperatureController {

    @Autowired
    private TemperatureRepository repository;

    @RequestMapping("/temperature")
    public List<Temperature> temperature(
            @RequestParam(value = "limit", defaultValue = "150") String limit,
            @RequestParam(value = "fromDate", required = false) String fromDate
    ) {
        List<Temperature> temperatureList = new ArrayList<>();
        temperatureList.addAll(repository.find(fromDate, Integer.valueOf(limit)));
        return temperatureList;
    }
}
