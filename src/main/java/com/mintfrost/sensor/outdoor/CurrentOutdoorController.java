package com.mintfrost.sensor.outdoor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
public class CurrentOutdoorController {

    @RequestMapping("/currentOutdoor")
    public List<Outdoor> currentOutdoor() {
        String s;
        Process p;
        Outdoor outdoor = new Outdoor(new Date(), "-999000");

        try {
            p = Runtime.getRuntime().exec("/home/pi/projects/temperature/read_temp.sh");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            s = br.readLine();
            if (s != null) {
                outdoor.setValue(s);
            }
            p.waitFor();
            p.destroy();
        } catch (Exception e) {
            System.out.println(e);
        }

        return Collections.singletonList(outdoor);
    }
}
