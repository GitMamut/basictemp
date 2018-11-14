package com.mintfrost.sensor.pressure;

import com.mintfrost.sensor.CommonSensorResponse;
import com.mintfrost.sensor.outdoor.CurrentOutdoorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.mintfrost.sensor.CommonSensorResponse.*;

@RestController
public class CurrentPressureController {

    @RequestMapping("/currentPressure")
    public List<Pressure> currentPressure() {
        String s;
        Process p;
        Pressure pressure = new Pressure(new Date(), "-999", "-999");

        PressureReading pressureReading = getSensorReading();
        if (pressureReading != null) {
            pressure.setTempValue(pressureReading.getTemperature());
            pressure.setPressureValue(pressureReading.getPressure());
        }

        return Collections.singletonList(pressure);
    }

    @RequestMapping("/commonIndoor2")
    public CommonSensorResponse commonIndoor2() {
        CommonSensorResponse commonSensorResponse = new CommonSensorResponse("indoor2", new Date());
        CurrentPressureController.PressureReading pressureReading = getSensorReading();
        if (pressureReading != null) {
            commonSensorResponse.getValues().add(new CommonSensorValue("temperature", pressureReading.getTemperature()));
            commonSensorResponse.getValues().add(new CommonSensorValue("pressure", pressureReading.getPressure()));
        }
        return commonSensorResponse;
    }

    private PressureReading getSensorReading() {
        Process p;
        String s;
        PressureReading pressureReading = null;
        try {
            p = Runtime.getRuntime().exec("/home/pi/projects/pressure/read_pressure.sh");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            s = br.readLine();
            if (s != null) {
                String[] tempPress = s.split(" ");
                if (tempPress.length == 2) {
                    pressureReading = new PressureReading(tempPress[0], tempPress[1]);
                }
            }
            p.waitFor();
            p.destroy();
        } catch (Exception e) {
            System.out.println(e);
        }
        return pressureReading;
    }

    private class PressureReading {
        private final String temperature;
        private final String pressure;

        private PressureReading(String temperature, String pressure) {
            this.temperature = temperature;
            this.pressure = pressure;
        }

        public String getTemperature() {
            return temperature;
        }

        public String getPressure() {
            return pressure;
        }
    }
}
