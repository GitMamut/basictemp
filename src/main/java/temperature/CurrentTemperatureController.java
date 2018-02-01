package temperature;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

@RestController
public class CurrentTemperatureController {

    @RequestMapping("/currentTemperature")
    public Temperature currentTemperature() {
        String s;
        Process p;
        Temperature temperature = new Temperature(new Date(), "-999000");

        try {
            p = Runtime.getRuntime().exec("/home/pi/projects/temperature/read_temp.sh");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            s = br.readLine();
            if (s != null) {
                temperature.setValue(String.format("%.1f", Double.valueOf(s) / 1000));
            }
            p.waitFor();
            p.destroy();
        } catch (Exception e) {
            System.out.println(e);
        }

        return temperature;
    }
}
