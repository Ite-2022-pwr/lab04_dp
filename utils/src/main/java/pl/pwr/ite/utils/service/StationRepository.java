package pl.pwr.ite.utils.service;

import pl.pwr.ite.utils.model.Station;

import java.util.List;

public interface StationRepository {
    List<Station> getAll();
    Station getById(Integer station);
    void loadData();
}
