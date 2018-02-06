package temperature;

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
            p = Runtime.getRuntime().exec("/home/pi/projects/indoor/read_humidity.sh");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            s = br.readLine();
            if (s != null) {
                String[] humTemp = s.split(" ");
                if (humTemp.length ==2) {
                    indoor.setHumValue(humTemp[0]);
                    indoor.setTempValue(humTemp[1]);
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
