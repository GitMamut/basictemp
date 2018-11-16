package com.mintfrost.sensor.outdoor;

import com.mintfrost.sensor.CommonSensorResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.mintfrost.sensor.CommonSensorResponse.*;
import static java.lang.String.*;

@RestController
public class CurrentOutdoorController {

    @RequestMapping("/currentOutdoor")
    public List<Outdoor> currentOutdoor() {
        String s;
        Process p;
        Outdoor outdoor = new Outdoor(new Date(), "-999000");

        OutdoorReading outdoorReading = getSensorReading();
        if (outdoorReading != null) {
            outdoor.setValue(outdoorReading.getTemperature());
        }

        return Collections.singletonList(outdoor);
    }

    @RequestMapping("/commonOutdoor")
    public CommonSensorResponse commonOutdoor() {
        CommonSensorResponse commonSensorResponse = new CommonSensorResponse("outdoor", new Date());
        OutdoorReading outdoorReading = getSensorReading();
        if (outdoorReading != null) {
            commonSensorResponse.getValues().add(
                    new CommonSensorValue(
                            "temperature",
                            format("%.1f", Double.valueOf(outdoorReading.getTemperature()) / 1000)));
        }
        return commonSensorResponse;
    }

    private OutdoorReading getSensorReading() {
        Process p;
        String s;
        OutdoorReading outdoorReading = null;
        try {
            p = Runtime.getRuntime().exec("/home/pi/projects/temperature/read_temp.sh");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            s = br.readLine();
            if (s != null) {
                outdoorReading = new OutdoorReading(s);
            }
            p.waitFor();
            p.destroy();
        } catch (Exception e) {
            System.out.println(e);
        }
        return outdoorReading;
    }

    private class OutdoorReading {
        private final String temperature;

        private OutdoorReading(String temperature) {
            this.temperature = temperature;
        }

        public String getTemperature() {
            return temperature;
        }
    }
}
