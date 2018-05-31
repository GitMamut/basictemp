package com.mintfrost.sensor.pressure;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PressureRepository extends MongoRepository<Pressure, String>, PressureRepositoryCustom {

}
