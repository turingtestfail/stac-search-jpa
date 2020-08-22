package com.jm.stacsearchjpa.model.repository;

import com.jm.stacsearchjpa.model.Feature;
import org.locationtech.jts.geom.Geometry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeatureRepository extends JpaRepository<Feature,String> {
    List<Feature> findAllByCollection_Id(String collectionId);
    Feature findByCollection_IdAndId(String collectionId,String id);
    @Query("select f from Feature f where intersects(f.geometry, ?1) = true")
    List<Feature>customByGeometry(Geometry geometry);
}
