package pl.pwr.ite.utils.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStCalcDate() {
        return stCalcDate;
    }

    public void setStCalcDate(String stCalcDate) {
        this.stCalcDate = stCalcDate;
    }

    public IndexLevel getStIndexLevel() {
        return stIndexLevel;
    }

    public void setStIndexLevel(IndexLevel stIndexLevel) {
        this.stIndexLevel = stIndexLevel;
    }

    public String getStSourceDataDate() {
        return stSourceDataDate;
    }

    public void setStSourceDataDate(String stSourceDataDate) {
        this.stSourceDataDate = stSourceDataDate;
    }

    public String getSo2CalcDate() {
        return so2CalcDate;
    }

    public void setSo2CalcDate(String so2CalcDate) {
        this.so2CalcDate = so2CalcDate;
    }

    public IndexLevel getSo2IndexLevel() {
        return so2IndexLevel;
    }

    public void setSo2IndexLevel(IndexLevel so2IndexLevel) {
        this.so2IndexLevel = so2IndexLevel;
    }

    public String getSo2SourceDataDate() {
        return so2SourceDataDate;
    }

    public void setSo2SourceDataDate(String so2SourceDataDate) {
        this.so2SourceDataDate = so2SourceDataDate;
    }

    public String getNo2CalcDate() {
        return no2CalcDate;
    }

    public void setNo2CalcDate(String no2CalcDate) {
        this.no2CalcDate = no2CalcDate;
    }

    public IndexLevel getNo2IndexLevel() {
        return no2IndexLevel;
    }

    public void setNo2IndexLevel(IndexLevel no2IndexLevel) {
        this.no2IndexLevel = no2IndexLevel;
    }

    public String getNo2SourceDataDate() {
        return no2SourceDataDate;
    }

    public void setNo2SourceDataDate(String no2SourceDataDate) {
        this.no2SourceDataDate = no2SourceDataDate;
    }

    public String getPm10CalcDate() {
        return pm10CalcDate;
    }

    public void setPm10CalcDate(String pm10CalcDate) {
        this.pm10CalcDate = pm10CalcDate;
    }

    public IndexLevel getPm10IndexLevel() {
        return pm10IndexLevel;
    }

    public void setPm10IndexLevel(IndexLevel pm10IndexLevel) {
        this.pm10IndexLevel = pm10IndexLevel;
    }

    public String getPm10SourceDataDate() {
        return pm10SourceDataDate;
    }

    public void setPm10SourceDataDate(String pm10SourceDataDate) {
        this.pm10SourceDataDate = pm10SourceDataDate;
    }

    public String getPm25CalcDate() {
        return pm25CalcDate;
    }

    public void setPm25CalcDate(String pm25CalcDate) {
        this.pm25CalcDate = pm25CalcDate;
    }

    public IndexLevel getPm25IndexLevel() {
        return pm25IndexLevel;
    }

    public void setPm25IndexLevel(IndexLevel pm25IndexLevel) {
        this.pm25IndexLevel = pm25IndexLevel;
    }

    public String getPm25SourceDataDate() {
        return pm25SourceDataDate;
    }

    public void setPm25SourceDataDate(String pm25SourceDataDate) {
        this.pm25SourceDataDate = pm25SourceDataDate;
    }

    public String getO3CalcDate() {
        return o3CalcDate;
    }

    public void setO3CalcDate(String o3CalcDate) {
        this.o3CalcDate = o3CalcDate;
    }

    public IndexLevel getO3IndexLevel() {
        return o3IndexLevel;
    }

    public void setO3IndexLevel(IndexLevel o3IndexLevel) {
        this.o3IndexLevel = o3IndexLevel;
    }

    public String getO3SourceDataDate() {
        return o3SourceDataDate;
    }

    public void setO3SourceDataDate(String o3SourceDataDate) {
        this.o3SourceDataDate = o3SourceDataDate;
    }
}
