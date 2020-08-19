package com.jm.stacsearchjpa.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Band {
    @Id
    private String name;
    private String common_name;
    private Double center_wavelength;
}
