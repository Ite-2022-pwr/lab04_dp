package pl.pwr.ite.utils.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;

@Data
@JsonIgnoreProperties(value = { "commune" })
public class City {

    private Integer id;

    private String name;
}
