package com.jm.stacsearchjpa.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
public class SunElevation {
    @Column(name = "min_elevation")
    private Double min;
    @Column(name = "max_elevation")
    private Double max;
}
