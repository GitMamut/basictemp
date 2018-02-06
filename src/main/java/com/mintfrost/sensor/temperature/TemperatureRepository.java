package com.mintfrost.sensor.temperature;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TemperatureRepository extends MongoRepository<Temperature, String>, TemperatureRepositoryCustom {

}
