package pl.pwr.ite.utils.service;

import pl.pwr.ite.utils.model.AirIndex;
import pl.pwr.ite.utils.model.Sensor;
import pl.pwr.ite.utils.model.SensorData;
import pl.pwr.ite.utils.model.Station;

import java.util.List;

public interface StationRepository {
    List<Station> getAll();
    List<Sensor> fetchSensors(Integer stationId);
    SensorData fetchSensorData(Integer sensorId);
    AirIndex fetchStationIndex(Integer stationId);
    Station getById(Integer station);
    void loadAllStations();
}
