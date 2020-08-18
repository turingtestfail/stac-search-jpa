package com.jm.stacsearchjpa.model;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Data
@TypeDef(
        name = "list-array",
        typeClass = ListArrayType.class
)
public class Collection {
    @Id
    private String id;
    private String stac_version;
    @Type(type = "list-array")
    @Column(
            name = "stac_extensions",
            columnDefinition = "text[]"
    )
    private List<String> stac_extensions;
    private String title;
    private String description;
    private String license;
    @Type(type = "list-array")
    @Column(
            name = "keywords",
            columnDefinition = "text[]"
    )
    private List<String> keywords;
    @ManyToMany(fetch= FetchType.EAGER)
    private List<Provider> providers;
    private Extent extent;
    private Summaries summaries;
    @ManyToMany(fetch= FetchType.EAGER)
    private List<Link> links;

}
