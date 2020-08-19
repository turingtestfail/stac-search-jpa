package com.jm.stacsearchjpa.model;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
@Data
@TypeDef(
        name = "list-array",
        typeClass = ListArrayType.class
)
public class Provider {
    @Id
    private String name;
    @Type(type = "list-array")
    @Column(
            name = "roles",
            columnDefinition = "text[]"
    )
    private List<String> roles;
    private String url;
}
