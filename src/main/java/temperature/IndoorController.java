package temperature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class IndoorController {

    @Autowired
    private IndoorRepository repository;

    @RequestMapping("/indoor")
    public List<Indoor> indoor(
            @RequestParam(value = "limit", defaultValue = "150") String limit,
            @RequestParam(value = "fromDate", required = false) String fromDate
    ) {
        List<Indoor> indoorList = new ArrayList<>();
        indoorList.addAll(repository.find(fromDate, Integer.valueOf(limit)));
        return indoorList;
    }
}
