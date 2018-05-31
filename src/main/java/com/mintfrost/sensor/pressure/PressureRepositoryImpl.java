package com.mintfrost.sensor.pressure;

import com.mintfrost.sensor.outdoor.Outdoor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.List;

public class PressureRepositoryImpl implements PressureRepositoryCustom {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Pressure> find(String date, Integer limit) {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "date"));

        if (limit != null) {
            query.limit(limit);
        }

        if (!StringUtils.isEmpty(date)) {
            try {
                query.addCriteria(new Criteria().where("date").lte(Outdoor.sdf.parse(date)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return mongoTemplate.find(query, Pressure.class);
    }
}
