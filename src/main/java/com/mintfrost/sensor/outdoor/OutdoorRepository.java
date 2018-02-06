package com.mintfrost.sensor.outdoor;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OutdoorRepository extends MongoRepository<Outdoor, String>, OutdoorRepositoryCustom {

}
