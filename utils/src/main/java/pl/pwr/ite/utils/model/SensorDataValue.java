package pl.pwr.ite.utils.model;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
public class SensorDataValue {

    private String date;

    private Double value;
}
