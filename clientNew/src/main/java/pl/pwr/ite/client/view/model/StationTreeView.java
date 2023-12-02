package pl.pwr.ite.client.view.model;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import pl.pwr.ite.utils.model.Station;

import java.util.*;
import java.util.function.Consumer;

import static java.util.stream.Collectors.groupingBy;

public class StationTreeView extends TreeView<String> {
    private Consumer<Station> stationClickConsumer;

    public void initializeTreeView(List<Station> stations, Consumer<Station> sensorClickConsumer) {
        Map<String, List<Station>> groupedStations = getGrouped(stations);
        var rootItem = new StationTreeItem("");
        rootItem.setExpanded(true);
        for(var set : groupedStations.entrySet()) {
            var name = set.getKey();
            var cityStations = set.getValue();
            var cityTreeItem = new StationTreeItem(name);
            for(var cityStation : cityStations) {
                cityTreeItem.getChildren().add(new StationTreeItem(cityStation));
            }
            rootItem.getChildren().add(cityTreeItem);
        }
        getSelectionModel().selectedItemProperty().addListener(this::treeItemSelected);
        this.stationClickConsumer = sensorClickConsumer;
        super.setRoot(rootItem);
    }

    private void treeItemSelected(ObservableValue<? extends TreeItem<String>> observableValue, TreeItem<String> oldValue, TreeItem<String> newValue) {
        if(newValue instanceof StationTreeItem treeItem) {
            if(TreeItemType.Sensor.equals(treeItem.getType())) {
                return;
            }
            if(TreeItemType.Station.equals(treeItem.getType())) {
                stationClickConsumer.accept(treeItem.getStation());
            }
        }
    }

    private Map<String, List<Station>> getGrouped(Collection<Station> stations) {
        return stations.stream().collect(groupingBy(s -> s.getCity().getName()));
    }
}
