package com.mintfrost.sensor.pressure;

import java.util.List;

public interface PressureRepositoryCustom {

    List<Pressure> find(String date, Integer limit);
}
