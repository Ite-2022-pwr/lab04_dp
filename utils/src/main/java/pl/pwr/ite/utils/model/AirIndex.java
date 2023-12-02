package pl.pwr.ite.utils.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;

@Data
@JsonIgnoreProperties(value = { "stIndexStatus", "stIndexCrParam" })
public class AirIndex {

    private Integer id;

    private String stCalcDate;

    private IndexLevel stIndexLevel;

    private String stSourceDataDate;

    private String so2CalcDate;

    private IndexLevel so2IndexLevel;

    private String so2SourceDataDate;

    private String no2CalcDate;

    private IndexLevel no2IndexLevel;

    private String no2SourceDataDate;

    private String pm10CalcDate;

    private IndexLevel pm10IndexLevel;

    private String pm10SourceDataDate;

    private String pm25CalcDate;

    private IndexLevel pm25IndexLevel;

    private String pm25SourceDataDate;

    private String o3CalcDate;

    private IndexLevel o3IndexLevel;

    private String o3SourceDataDate;
}
