package pl.pwr.ite.utils.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@JsonIgnoreProperties(value = { "gegrLat", "gegrLon", "addressStreet" })
public class Station {

    private Integer id;

    private String stationName;

    private City city;

    @JsonIgnore
    private List<Sensor> sensors;

    private AirIndex index;
}
