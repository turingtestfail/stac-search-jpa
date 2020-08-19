package com.jm.stacsearchjpa.model;

import lombok.Data;
import org.locationtech.jts.geom.Polygon;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Extent {
    @Id
    private int id;
    @Column(columnDefinition = "polygon")
    private Polygon spatial;
}
