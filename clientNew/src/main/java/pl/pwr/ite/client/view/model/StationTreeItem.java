package pl.pwr.ite.client.view.model;

import javafx.scene.control.TreeItem;
import pl.pwr.ite.utils.model.City;
import pl.pwr.ite.utils.model.Sensor;
import pl.pwr.ite.utils.model.Station;

public class StationTreeItem extends TreeItem<String> {

    private final Sensor sensor;
    private final Station station;
    private final City city;
    private final String label;
    private final TreeItemType type;
    public StationTreeItem(Station station) {
        this(null, null, null, station, TreeItemType.Station);
    }

    public StationTreeItem(Sensor sensor) {
        this(null, null, sensor, null, TreeItemType.Sensor);
    }

    public StationTreeItem(City city) {
        this(null, city, null, null, TreeItemType.City);
    }

    public StationTreeItem(String value) {
        this(value, null, null, null, TreeItemType.Value);
    }

    private StationTreeItem(String value, City city, Sensor sensor, Station station, TreeItemType type) {
        this.sensor = sensor;
        this.station = station;
        this.type = type;
        this.city = city;
        this.label = value;
        setValue();
    }

    private void setValue() {
        var value = switch (this.type) {
            case Station -> station.getId() + " | " + station.getStationName();
            case Sensor -> sensor.getId() + " | " + sensor.getParam().getParamCode();
            case City -> city.getName();
            case Value -> this.label;
        };
        super.setValue(value);
    }

    public Station getStation() {
        return station;
    }

    public TreeItemType getType() {
        return type;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public City getCity() {
        return city;
    }

    public String getLabel() {
        return label;
    }
}
