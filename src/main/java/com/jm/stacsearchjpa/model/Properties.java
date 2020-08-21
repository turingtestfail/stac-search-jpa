package com.jm.stacsearchjpa.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jm.stacsearchjpa.util.BandsJsonSerializer;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Embeddable
@Data
public class Properties {
    private Date datetime;
    private String title;
    private String license;
    @ManyToMany(fetch= FetchType.EAGER)
    private Set<Provider> providers;
    @JsonProperty("view:sun_azimuth")
    private Double sunazimuth;
    @JsonProperty("eo:cloud_cover")
    private Double cloudcover;

    @JsonProperty("view:off_nadir")
    private Double offnadir;

    private String platform;

    @Type(type = "list-array")
    @Column(
            name = "instruments",
            columnDefinition = "text[]"
    )
    private List<String> instruments;

    @JsonProperty("eo:bands")
    @ManyToMany(fetch= FetchType.EAGER)
    @JsonSerialize(using= BandsJsonSerializer.class)
    private Set<Band> bands;

    @JsonProperty("view:sun_elevation")
    private Double sun_elevation;

    @JsonProperty("eo:gsd")
    private Double gsd;

}
