package com.jm.stacsearchjpa.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
public class OffNadir {
    @Column(name = "min_nadir")
    private Integer min;
    @Column(name = "max_nadir")
    private Integer max;
}
