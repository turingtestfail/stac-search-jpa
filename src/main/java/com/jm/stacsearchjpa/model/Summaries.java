package com.jm.stacsearchjpa.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.List;

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

}
