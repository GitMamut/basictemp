package com.mintfrost.sensor.indoor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
public class CurrentIndoorController {

    @RequestMapping("/currentIndoor")
    public List<Indoor> currentIndoor() {
        String s;
        Process p;
        Indoor indoor = new Indoor(new Date(), "-999", "-999");

        try {
            p = Runtime.getRuntime().exec("/home/pi/projects/indoor/read_indoor.sh");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            s = br.readLine();
            if (s != null) {
                String[] tempHum = s.split(" ");
                if (tempHum.length ==2) {
                    indoor.setTempValue(tempHum[0]);
                    indoor.setHumValue(tempHum[1]);
                }
            }
            p.waitFor();
            p.destroy();
        } catch (Exception e) {
            System.out.println(e);
        }

        return Collections.singletonList(indoor);
    }
}
