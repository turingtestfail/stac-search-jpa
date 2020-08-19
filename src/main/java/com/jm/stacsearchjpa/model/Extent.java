package com.jm.stacsearchjpa.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jm.stacsearchjpa.util.BboxJsonSerializer;
import org.hibernate.annotations.Type;
import org.locationtech.jts.geom.Polygon;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Extent {
    @Column(columnDefinition = "geometry(Polygon,4326)")
    @JsonSerialize(using= BboxJsonSerializer.class)
    private Polygon spatial;

}
