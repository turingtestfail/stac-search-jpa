package com.jm.stacsearchjpa.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jm.stacsearchjpa.util.BandsJsonSerializer;
import com.jm.stacsearchjpa.util.BboxJsonSerializer;
import com.jm.stacsearchjpa.util.BboxWithoutTagJsonSerializer;
import com.jm.stacsearchjpa.util.CollectionIdOnlyJsonSerializer;
import com.jm.stacsearchjpa.util.PolygonToGeoJsonSerializer;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.locationtech.jts.geom.Polygon;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Feature {
    @Id
    private String id;
    private String stac_version;
    @Type(type = "list-array")
    @Column(
            name = "stac_extensions",
            columnDefinition = "text[]"
    )
    private List<String> stac_extensions;
    private String type;
    @Column(columnDefinition = "geometry(Polygon,4326)")
    @JsonSerialize(using= BboxWithoutTagJsonSerializer.class)
    private Polygon bbox;
    @Column(columnDefinition = "geometry(Polygon,4326)")
    @JsonSerialize(using= PolygonToGeoJsonSerializer.class)
    private Polygon geometry;
    @Embedded
    private Properties properties;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonSerialize(using= CollectionIdOnlyJsonSerializer.class)
    private Collection collection;
    @ManyToMany(fetch= FetchType.EAGER)
    private Set<Link> links;

}
