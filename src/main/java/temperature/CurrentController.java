package temperature;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@RestController
public class CurrentController {
    @RequestMapping("/currentTemp")
    public Temperature currentTemp() {
        String s;
        Process p;
        Temperature temperature = new Temperature("now", "Could not acquire temperature");

        try {
            p = Runtime.getRuntime().exec("/home/pi/projects/temperature/read_temp.sh");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            s = br.readLine();
            temperature.setValue(s);
            System.out.println("line: " + s);
            p.waitFor();
            System.out.println("exit: " + p.exitValue());
            p.destroy();
        } catch (Exception e) {
            System.out.println(e);
        }

        return temperature;

    }
}
