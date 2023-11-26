package pl.pwr.ite.client.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import pl.pwr.ite.client.view.model.StationTreeView;
import pl.pwr.ite.utils.model.*;
import pl.pwr.ite.utils.model.SensorData;
import pl.pwr.ite.utils.service.StationRepository;
import pl.pwr.ite.utils.service.WebClient;
import pl.pwr.ite.utils.service.impl.StationRepositoryImpl;
import pl.pwr.ite.utils.service.impl.WebClientImpl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HelloController {
    private final WebClient webClient = WebClientImpl.getInstance();
    private final StationRepository stationRepository = StationRepositoryImpl.getInstance();

    @FXML protected StationTreeView stationsTreeView;
    @FXML protected BarChart<String, Double> stationBarChart;
    @FXML protected TextField cityNameTextField;
    @FXML protected Label totalStationsLabel;

    @FXML
    protected void onTestClick(ActionEvent event) {
        var data = stationRepository.getAll().stream().filter(s -> s.getCity().getName().contains(cityNameTextField.getText())).toList();
        totalStationsLabel.setText("Stations in database: " + data.size());
        stationsTreeView.test(data, this::stationSelected);
    }

    private void stationSelected(Station station) {
        stationBarChart.getData().clear();
        stationBarChart.setTitle("Air quality index for '" + station.getStationName() + "'");
        try {
            var sensors = webClient.get("/station/sensors/" + station.getId(), Sensor[].class);
            station.setSensors(List.of(sensors));
            for(var sensor : sensors) {
                var data = webClient.get("/data/getData/" + sensor.getId(), SensorData.class);
                sensor.setSensorData(data);
            }
            var index = webClient.get("/aqindex/getIndex/" + station.getId(), AirIndex.class);
            var series = new XYChart.Series<String, Double>();
            stationBarChart.getData().add(series);
            addXYData(series, station, "ST", index.getStSourceDataDate(), index.getStIndexLevel());
            addXYData(series, station, "SO2", index.getSo2SourceDataDate(), index.getSo2IndexLevel());
            addXYData(series, station, "NO2", index.getNo2SourceDataDate(), index.getNo2IndexLevel());
            addXYData(series, station, "PM10", index.getPm10SourceDataDate(), index.getPm10IndexLevel());
            addXYData(series, station, "PM25", index.getPm25SourceDataDate(), index.getPm25IndexLevel());
            addXYData(series, station, "O3", index.getO3SourceDataDate(), index.getO3IndexLevel());
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void addXYData(XYChart.Series<String, Double> series, Station station, String key, String sourceDataDate, IndexLevel indexLevel) {
        var sensorData = getDataByKeyAndTime(station, key, sourceDataDate);
        if(sensorData != null) {
            var data = new XYChart.Data<>(key + " (" + indexLevel.getIndexLevelName() + ")", sensorData.getValue());
            series.getData().add(data);
            pickBarColor(data, indexLevel.getId());
        }
    }

    private void pickBarColor(XYChart.Data<String, Double> data, Integer id) {
        var barColor = switch (id) {
            case -1 -> "#DCDCDC";
            case 0 -> "#90EE90";
            case 1 -> "#32CD32";
            case 2 -> "#3CB371";
            case 3 -> "#FF0000";
            case 4 -> "8000000";
            case 5 -> "#000000";
            default -> throw new IllegalStateException("Unexpected value: " + id);
        };
        for(var node : data.getNode().lookupAll(".default-color0.chart-bar")) {
            node.setStyle("-fx-bar-fill: " + barColor);
        }
    }

    private SensorDataValue getDataByKeyAndTime(Station station, String key, String time) {
        return station.getSensors().stream()
                .filter(s -> s.getParam().getParamCode().equals(key))
                .map(Sensor::getSensorData)
                .map(SensorData::getValues)
                .flatMap(Arrays::stream)
                .filter(rv -> rv.getDate().equals(time))
                .findFirst()
                .orElse(null);
    }
}
