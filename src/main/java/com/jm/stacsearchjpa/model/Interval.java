package com.jm.stacsearchjpa.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
@Data
public class Interval {
    @Column(name = "min_interval")
    private Date min;
    @Column(name = "max_interval")
    private Date max;
}
