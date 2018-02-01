package temperature;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
public class CurrentTemperatureController {

    @RequestMapping("/currentTemperature")
    public List<Temperature> currentTemperature() {
        String s;
        Process p;
        Temperature temperature = new Temperature(new Date(), "-999000");

        try {
            p = Runtime.getRuntime().exec("/home/pi/projects/temperature/read_temp.sh");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            s = br.readLine();
            if (s != null) {
                temperature.setValue(s);
            }
            p.waitFor();
            p.destroy();
        } catch (Exception e) {
            System.out.println(e);
        }

        return Collections.singletonList(temperature);
    }
}
