package pl.pwr.ite.utils.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IndexLevel {

    private Integer id;

    private String indexLevelName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIndexLevelName() {
        return indexLevelName;
    }

    public void setIndexLevelName(String indexLevelName) {
        this.indexLevelName = indexLevelName;
    }
}
