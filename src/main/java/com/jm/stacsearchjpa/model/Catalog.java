package com.jm.stacsearchjpa.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Data
/**
 * Root or landing page of the STAC
 * @see <a href="https://github.com/radiantearth/stac-spec/tree/master/catalog-spec">STAC Catalogs</a>
 * @see <a href="https://stacspec.org/STAC-api.html#tag/Capabilities">Capabilities</a>
 */
public class Catalog {
    @Id
    private String id;
    private String title;
    private String stac_version;
    @ManyToMany(fetch=FetchType.EAGER)
    private List<Link> links;
    private String description;
}
