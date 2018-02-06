package com.mintfrost.sensor.indoor;


import org.springframework.data.mongodb.core.mapping.Document;

import java.text.SimpleDateFormat;
import java.util.Date;

@Document(collection = "indoor")
public class Indoor {

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");

    private Date date;

    private String humValue;

    private String tempValue;

    public Indoor(Date date, String humValue, String tempValue) {
        this.date = date;
        this.humValue = humValue;
        this.tempValue = tempValue;
    }

    public String getTempValue() {
        return tempValue;
    }

    public void setTempValue(String tempValue) {
        this.tempValue = tempValue;
    }

    public String getDate() {
        return sdf.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHumValue() {
        return humValue;
    }

    public void setHumValue(String humValue) {
        this.humValue = humValue;
    }
}
