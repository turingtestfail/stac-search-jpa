package com.jm.stacsearchjpa.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jm.stacsearchjpa.util.BboxJsonSerializer;
import com.jm.stacsearchjpa.util.IntervalJsonSerializer;
import lombok.Data;
import org.locationtech.jts.geom.Polygon;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Data
public class Extent {
    @Column(columnDefinition = "geometry(Polygon,4326)")
    @JsonSerialize(using= BboxJsonSerializer.class)
    private Polygon spatial;
    @Embedded
    @JsonSerialize(using= IntervalJsonSerializer.class)
    private Interval temporal;

}
