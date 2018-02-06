package com.mintfrost.sensor.indoor;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IndoorRepository extends MongoRepository<Indoor, String>, IndoorRepositoryCustom {

}
