package pl.pwr.ite.utils.model;

import lombok.Data;
import lombok.Getter;

@Data
public class SensorData {

    private String key;

    private SensorDataValue[] values;
}
