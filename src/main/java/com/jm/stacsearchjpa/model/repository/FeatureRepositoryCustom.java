package com.jm.stacsearchjpa.model.repository;

import com.jm.stacsearchjpa.model.Feature;
import org.geotools.data.jdbc.FilterToSQLException;
import org.geotools.filter.text.cql2.CQLException;
import org.locationtech.jts.geom.Geometry;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface FeatureRepositoryCustom {
    List<Feature> stacSearch(@Param("query")String query,
                             @Param("bbox") Geometry bbox,@Param("geometry") Geometry geometry,
                             @Param("datetime") String datetime,
                                             @Param("ids") List<String> ids,
                                             @Param("collections")List<String>collections);

    List<Feature>cqlSearch(String cql) throws CQLException, FilterToSQLException;
}
