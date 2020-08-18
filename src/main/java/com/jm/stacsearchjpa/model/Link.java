package com.jm.stacsearchjpa.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@Data
@IdClass(LinkId.class)
public class Link {
    @Id
    private String rel;
    @Id
    private String href;
    private String type;
    private String title;
}
