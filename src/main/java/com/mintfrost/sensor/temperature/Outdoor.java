package com.mintfrost.sensor.temperature;


import org.springframework.data.mongodb.core.mapping.Document;

import java.text.SimpleDateFormat;
import java.util.Date;

@Document(collection = "outdoor")
public class Outdoor {

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");

    private java.util.Date date;

    private String value;

    public Outdoor(Date date, String value) {
        this.date = date;
        this.value = value;
    }

    public String getDate() {
        return sdf.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getValue() {
        return String.format("%.1f", Double.valueOf(value) / 1000);
    }

    public void setValue(String value) {
        this.value = value;
    }
}
