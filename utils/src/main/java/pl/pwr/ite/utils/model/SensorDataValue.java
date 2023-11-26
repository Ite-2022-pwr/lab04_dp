package pl.pwr.ite.utils.model;

import java.time.LocalDateTime;

public class SensorDataValue {

    private String date;

    private Double value;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
