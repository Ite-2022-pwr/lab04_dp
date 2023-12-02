package pl.pwr.ite.utils.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class Sensor {

    private Integer id;

    private Integer stationId;

    private Param param;

    @JsonIgnore
    private SensorData sensorData;
}

