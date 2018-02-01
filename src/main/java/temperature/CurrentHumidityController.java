package temperature;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
public class CurrentHumidityController {

    @RequestMapping("/currentHumidity")
    public List<Humidity> currentHumidity() {
        String s;
        Process p;
        Humidity humidity = new Humidity(new Date(), "-999000");

        try {
            p = Runtime.getRuntime().exec("/home/pi/projects/humidity/read_humidity.sh");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            s = br.readLine();
            if (s != null) {
                humidity.setValue(s);
            }
            p.waitFor();
            p.destroy();
        } catch (Exception e) {
            System.out.println(e);
        }

        return Collections.singletonList(humidity);
    }
}
