package temperature;

import java.util.List;

public interface TemperatureRepositoryCustom {

    List<Temperature> find(String date, Integer limit);
}
