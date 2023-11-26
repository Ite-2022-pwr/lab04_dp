package pl.pwr.ite.utils.service.impl;

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
    public void loadData() {
        try {
            this.stations = List.of(webClient.get("/station/findAll", Station[].class));
        } catch (IOException | InterruptedException ex) {
            throw new RuntimeException("Couldn't load station list from external API", ex);
        }
    }

    public static StationRepository getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new StationRepositoryImpl();
        }
        return INSTANCE;
    }
}
