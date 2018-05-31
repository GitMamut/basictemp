package com.mintfrost.sensor.pressure;

import org.springframework.data.mongodb.core.mapping.Document;

import java.text.SimpleDateFormat;
import java.util.Date;

@Document(collection = "pressure")
public class Pressure {

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");

    private Date date;

    private String pressureValue;

    private String tempValue;

    public Pressure(Date date, String tempValue, String pressureValue) {
        this.date = date;
        this.pressureValue = pressureValue;
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

    public String getPressureValue() {
        return pressureValue;
    }

    public void setPressureValue(String pressureValue) {
        this.pressureValue = pressureValue;
    }
}
