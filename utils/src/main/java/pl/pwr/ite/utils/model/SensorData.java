package pl.pwr.ite.utils.model;

public class SensorData {

    private String key;

    private SensorDataValue[] values;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public SensorDataValue[] getValues() {
        return values;
    }

    public void setValues(SensorDataValue[] values) {
        this.values = values;
    }
}
