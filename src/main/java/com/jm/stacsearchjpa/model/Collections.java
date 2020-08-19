package com.jm.stacsearchjpa.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Data
public class Collections {
    @Id
    @Column(name="coll_id")
    private int id;
    @ManyToMany(fetch= FetchType.EAGER)
    private Set<Link> links;
    @ManyToMany(fetch=FetchType.EAGER)
    private Set<Collection> collections;
}
