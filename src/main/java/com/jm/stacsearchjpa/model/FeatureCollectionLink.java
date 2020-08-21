package com.jm.stacsearchjpa.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;


@Data
public class FeatureCollectionLink {

    private String rel;
    private String hreflang;
    private String href;
    private String type;
    private String title;
    private String method;
    private Integer length;
    private Headers headers;
    private boolean merge;
}
