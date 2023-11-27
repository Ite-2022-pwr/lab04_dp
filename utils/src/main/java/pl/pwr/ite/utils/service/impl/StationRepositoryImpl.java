package pl.pwr.ite.utils.service.impl;

import pl.pwr.ite.utils.model.AirIndex;
import pl.pwr.ite.utils.model.Sensor;
import pl.pwr.ite.utils.model.SensorData;
import pl.pwr.ite.utils.model.Station;
import pl.pwr.ite.utils.service.StationRepository;
import pl.pwr.ite.utils.service.WebClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StationRepositoryImpl implements StationRepository {
    private static StationRepository INSTANCE = null;
    private final WebClient webClient = WebClientImpl.getInstance();
    private List<Station> stations = new ArrayList<>();

    @Override
    public List<Station> getAll() {
        return this.stations;
    }

    @Override
    public Station getById(Integer stationId) {
        return stations.stream().filter(s -> s.getId().equals(stationId)).findFirst().orElse(null);
    }

    @Override
    public void loadAllStations() {
        this.stations = List.of(webClient.get("/station/findAll", Station[].class));
    }

    @Override
    public List<Sensor> fetchSensors(Integer stationId) {
        return List.of(webClient.get("/station/sensors/" + stationId, Sensor[].class));
    }

    @Override
    public SensorData fetchSensorData(Integer sensorId) {
        return webClient.get("/data/getData/" + sensorId, SensorData.class);
    }

    @Override
    public AirIndex fetchStationIndex(Integer stationId) {
        return webClient.get("/aqindex/getIndex/" + stationId, AirIndex.class);
    }

    public static StationRepository getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new StationRepositoryImpl();
        }
        return INSTANCE;
    }
}
