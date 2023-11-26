package pl.pwr.ite.utils.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class Sensor {

    private Integer id;

    private Integer stationId;

    private Param param;

    @JsonIgnore
    private SensorData sensorData;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public Param getParam() {
        return param;
    }

    public void setParam(Param param) {
        this.param = param;
    }

    public SensorData getSensorData() {
        return sensorData;
    }

    public void setSensorData(SensorData sensorData) {
        this.sensorData = sensorData;
    }
}

