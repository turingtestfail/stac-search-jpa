package com.jm.stacsearchjpa.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
@Data
public class DateTime {
    @Column(name = "min_ts")
    private Date min;
    @Column(name = "max_ts")
    private Date max;
}
