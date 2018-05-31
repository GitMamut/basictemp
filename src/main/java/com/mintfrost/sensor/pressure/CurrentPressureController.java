package com.mintfrost.sensor.pressure;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
public class CurrentPressureController {

    @RequestMapping("/currentPressure")
    public List<Pressure> currentPressure() {
        String s;
        Process p;
        Pressure pressure = new Pressure(new Date(), "-999", "-999");

        try {
            p = Runtime.getRuntime().exec("/home/pi/projects/pressure/read_pressure.sh");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            s = br.readLine();
            if (s != null) {
                String[] tempPress = s.split(" ");
                if (tempPress.length ==2) {
                    pressure.setTempValue(tempPress[0]);
                    pressure.setPressureValue(tempPress[1]);
                }
            }
            p.waitFor();
            p.destroy();
        } catch (Exception e) {
            System.out.println(e);
        }

        return Collections.singletonList(pressure);
    }
}
