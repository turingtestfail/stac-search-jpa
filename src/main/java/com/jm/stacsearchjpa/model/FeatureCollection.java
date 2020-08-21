package com.jm.stacsearchjpa.model;


import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class FeatureCollection {

    private String type;
    private List<Feature> features;
    private List<FeatureCollectionLink> links;
    private Date timeStamp;
    private Integer numberMatched;
    private Integer numberReturned;

}
