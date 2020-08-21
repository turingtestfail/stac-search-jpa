package com.jm.stacsearchjpa.model;

import lombok.Data;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Data
public class Assets {
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "href", column = @Column(name = "feature_analytic_asset_href")),
            @AttributeOverride( name = "title", column = @Column(name = "feature_analytic_asset_title")),
    })
    private Asset analytic;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "href", column = @Column(name = "feature_thumbnail_asset_href")),
            @AttributeOverride( name = "title", column = @Column(name = "feature_thumbnail_asset_title")),
    })
    private Asset thumbnail;
}
