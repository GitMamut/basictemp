package temperature;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrentController {
    @RequestMapping("/currentTemp")
    public Temperature currentTemp() {
        return new Temperature("now", "2222");

    }
}
