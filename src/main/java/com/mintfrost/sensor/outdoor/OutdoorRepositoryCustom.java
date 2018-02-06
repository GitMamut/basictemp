package com.mintfrost.sensor.outdoor;

import java.util.List;

public interface OutdoorRepositoryCustom {

    List<Outdoor> find(String date, Integer limit);
}
