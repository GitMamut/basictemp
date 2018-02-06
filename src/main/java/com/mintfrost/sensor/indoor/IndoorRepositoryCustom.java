package com.mintfrost.sensor.indoor;

import java.util.List;

public interface IndoorRepositoryCustom {

    List<Indoor> find(String date, Integer limit);
}
