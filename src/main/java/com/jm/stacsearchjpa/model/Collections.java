package com.jm.stacsearchjpa.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.List;
@Entity
@Data
public class Collections {
    @ManyToMany(fetch= FetchType.EAGER)
    private List<Link> links;
    @ManyToMany(fetch=FetchType.EAGER)
    private List<Collection> collections;
}
