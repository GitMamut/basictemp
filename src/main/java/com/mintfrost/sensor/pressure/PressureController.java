package com.mintfrost.sensor.pressure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class PressureController {

    @Autowired
    private PressureRepository repository;

    @RequestMapping("/pressure")
    public List<Pressure> pressure(
            @RequestParam(value = "limit", defaultValue = "150") String limit,
            @RequestParam(value = "fromDate", required = false) String fromDate
    ) {
        List<Pressure> pressureList = new ArrayList<>();
        pressureList.addAll(repository.find(fromDate, Integer.valueOf(limit)));
        return pressureList;
    }
}
