package com.mintfrost.sensor.indoor;

import com.mintfrost.sensor.CommonSensorResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.mintfrost.sensor.CommonSensorResponse.*;

@CrossOrigin
@RestController
public class CurrentIndoorController {

    @RequestMapping("/currentIndoor")
    public List<Indoor> currentIndoor() {
        String s;
        Process p;
        Indoor indoor = new Indoor(new Date(), "-999", "-999");

        IndoorReading indoorReading = getSensorReading();
        if (indoorReading != null) {
            indoor.setTempValue(indoorReading.getTemperature());
            indoor.setHumValue(indoorReading.getHumidity());
        }

        return Collections.singletonList(indoor);
    }

    @RequestMapping("/commonIndoor1")
    public CommonSensorResponse commonIndoor1() {
        CommonSensorResponse commonSensorResponse = new CommonSensorResponse("indoor1", new Date());
        IndoorReading indoorReading = getSensorReading();
        if (indoorReading != null) {
            commonSensorResponse.getValues().add(new CommonSensorValue("temperature", indoorReading.getTemperature()));
            commonSensorResponse.getValues().add(new CommonSensorValue("humidity", indoorReading.getHumidity()));
        }
        return commonSensorResponse;
    }

    private IndoorReading getSensorReading() {
        Process p;
        String s;
        IndoorReading indoorReading = null;
        try {
            p = Runtime.getRuntime().exec("/home/pi/projects/indoor/read_indoor.sh");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            s = br.readLine();
            if (s != null) {
                String[] tempHum = s.split(" ");
                if (tempHum.length == 2) {
                    indoorReading = new IndoorReading(tempHum[0], tempHum[1]);
                }

            }
            p.waitFor();
            p.destroy();
        } catch (Exception e) {
            System.out.println(e);
        }
        return indoorReading;
    }

    private class IndoorReading {
        private final String temperature;
        private final String humidity;

        private IndoorReading(String temperature, String humidity) {
            this.temperature = temperature;
            this.humidity = humidity;
        }

        public String getTemperature() {
            return temperature;
        }

        public String getHumidity() {
            return humidity;
        }
    }
}
