package com.jm.stacsearchjpa.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Asset {
    private String href;
    private String title;
}
