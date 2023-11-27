package pl.pwr.ite.client.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
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
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class HelloController {
    private final WebClient webClient = WebClientImpl.getInstance();
    private final StationRepository stationRepository = StationRepositoryImpl.getInstance();

    @FXML protected StationTreeView stationsTreeView;
    @FXML protected BarChart<String, Double> stationBarChart;
    @FXML protected TextField cityNameTextField;
    @FXML protected Label totalStationsLabel;
    @FXML protected VBox centerVBox;
    @FXML protected TabPane dataTabPane;

    @FXML
    protected void onTestClick(ActionEvent event) {
        var data = stationRepository.getAll().stream().filter(s -> s.getCity().getName().contains(cityNameTextField.getText())).toList();
        totalStationsLabel.setText("Stations in database: " + data.size());
        stationsTreeView.test(data, this::stationSelected);
    }

    private void stationSelected(Station station) {
        stationBarChart.getData().clear();
        stationBarChart.setTitle("Air quality index for '" + station.getStationName() + "'");
        station = loadStationData(station);
        createIndexChart(station, station.getIndex());
        createDataTabs(station);
    }

    private Station loadStationData(Station station) {
        var sensors = stationRepository.fetchSensors(station.getId());
        station.setSensors(sensors);
        for(var sensor : sensors) {
            var data = stationRepository.fetchSensorData(sensor.getId());
            sensor.setSensorData(data);
        }
        var index = stationRepository.fetchStationIndex(station.getId());
        station.setIndex(index);

        return station;
    }

    private void createDataTabs(Station station) {
        dataTabPane.getTabs().clear();
        for(var sensor : station.getSensors()) {
            var sensorData = sensor.getSensorData();
            var paramCode = sensor.getParam().getParamCode();

            var xAxis = new CategoryAxis();
            var yAxis = new NumberAxis();
            var chart = new ScatterChart<>(xAxis, yAxis);
            chart.setTitle("Data over time for '" + paramCode + "'");
            var series = new XYChart.Series<String, Number>();
            series.setName(paramCode + " value");
            for(var value : sensorData.getValues()) {
                if(value.getValue() != null) {
                    series.getData().add(new XYChart.Data<>(value.getDate(), value.getValue()));
                } else {
                    series.getData().add(new XYChart.Data<>(value.getDate(), 0.0));
                }
            }
            chart.getData().add(series);
            for(var data : series.getData()) {
                data.getNode().setScaleX(0.5);
                data.getNode().setScaleY(0.5);
            }
            var tab = new Tab(paramCode, chart);
            dataTabPane.getTabs().add(tab);
        }
    }

    private void createIndexChart(Station station, AirIndex index) {
        var series = new XYChart.Series<String, Double>();
        stationBarChart.getData().add(series);
        addXYData(series, station, "ST", index.getStSourceDataDate(), index.getStIndexLevel());
        addXYData(series, station, "SO2", index.getSo2SourceDataDate(), index.getSo2IndexLevel());
        addXYData(series, station, "NO2", index.getNo2SourceDataDate(), index.getNo2IndexLevel());
        addXYData(series, station, "PM10", index.getPm10SourceDataDate(), index.getPm10IndexLevel());
        addXYData(series, station, "PM25", index.getPm25SourceDataDate(), index.getPm25IndexLevel());
        addXYData(series, station, "O3", index.getO3SourceDataDate(), index.getO3IndexLevel());
    }

    private void addXYData(XYChart.Series<String, Double> series, Station station, String key, String sourceDataDate, IndexLevel indexLevel) {
        var sensorData = getDataByKeyAndTime(station, key, sourceDataDate);
        if(sensorData != null) {
            var data = new XYChart.Data<>(key + " (" + indexLevel.getIndexLevelName() + ")", sensorData.getValue());
            series.getData().add(data);
            var barColor = switch (indexLevel.getId()) {
                case -1 -> "#DCDCDC";
                case 0 -> "#32CD32";
                case 1 -> "#90EE90";
                case 2 -> "#3CB371";
                case 3 -> "#FF0000";
                case 4 -> "8000000";
                case 5 -> "#000000";
                default -> throw new IllegalStateException("Unexpected value: " + indexLevel.getId());
            };
            for(var node : data.getNode().lookupAll(".default-color0.chart-bar")) {
                node.setStyle("-fx-bar-fill: " + barColor);
            }
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
