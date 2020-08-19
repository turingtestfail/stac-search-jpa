package com.jm.stacsearchjpa.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jm.stacsearchjpa.util.BandsJsonSerializer;
import com.jm.stacsearchjpa.util.IntervalJsonSerializer;
import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.Set;

@Embeddable
@Data
@TypeDef(
        name = "list-array",
        typeClass = ListArrayType.class
)
public class Summaries {
    @Embedded
    DateTime datetime;

    @JsonProperty("sci:citation")
    @Type(type = "list-array")
    @Column(
            name = "citation",
            columnDefinition = "text[]"
    )
    private List<String> citation;

    @JsonProperty("eo:gsd")
    @Type(type = "list-array")
    @Column(
            name = "gsd",
            columnDefinition = "integer[]"
    )
    private List<Integer> gsd;

    @Type(type = "list-array")
    @Column(
            name = "platform",
            columnDefinition = "text[]"
    )
    private List<String> platform;

    @Type(type = "list-array")
    @Column(
            name = "constellation",
            columnDefinition = "text[]"
    )
    private List<String> constellation;

    @Type(type = "list-array")
    @Column(
            name = "instruments",
            columnDefinition = "text[]"
    )
    private List<String> instruments;

    @Embedded
    @JsonProperty("view:off_nadir")
    private OffNadir offNadir;

    @Embedded
    @JsonProperty("view:sun_elevation")
    private SunElevation sunElevation;


    @JsonProperty("eo:bands")
    @ManyToMany(fetch= FetchType.EAGER)
    @JsonSerialize(using= BandsJsonSerializer.class)
    private Set<Band> bands;

}
