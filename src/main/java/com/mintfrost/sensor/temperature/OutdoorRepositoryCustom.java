package com.mintfrost.sensor.temperature;

import java.util.List;

public interface OutdoorRepositoryCustom {

    List<Outdoor> find(String date, Integer limit);
}
