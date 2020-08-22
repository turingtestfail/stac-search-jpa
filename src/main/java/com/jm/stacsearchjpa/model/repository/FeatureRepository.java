package com.jm.stacsearchjpa.model.repository;

import com.jm.stacsearchjpa.model.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeatureRepository extends JpaRepository<Feature,String> {
    List<Feature> findAllByCollection_Id(String collectionId);
    Feature findByCollection_IdAndId(String collectionId,String id);
}
