package com.jm.stacsearchjpa.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jm.stacsearchjpa.util.GeoJsonToGeometryDeserializer;
import lombok.Data;
import org.locationtech.jts.geom.Geometry;

import java.util.List;

@Data
public class StacSearch {
    public StacSearch(){
    }
    @JsonProperty
    private double[]bbox;
    @JsonProperty
    private String datetime;
    @JsonProperty
    @JsonDeserialize(using= GeoJsonToGeometryDeserializer.class)
    private Geometry intersects;
    @JsonProperty
    private List<String>collections;
    @JsonProperty
    private List<String> ids;
    @JsonProperty
    private Integer limit;
}
