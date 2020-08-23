package com.jm.stacsearchjpa.controller;

import com.jm.stacsearchjpa.model.Feature;
import com.jm.stacsearchjpa.model.FeatureCollection;
import com.jm.stacsearchjpa.model.StacSearch;
import com.jm.stacsearchjpa.model.repository.FeatureRepository;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.referencing.CRS;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateXY;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;
import org.opengis.referencing.FactoryException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class SearchController {
    private final FeatureRepository featureRepository;
    public SearchController(FeatureRepository featureRepository){
        this.featureRepository=featureRepository;
    }
    @GetMapping("/search")
    public FeatureCollection searchByGet(@RequestParam(required = false)double[]bbox,
                                         @RequestParam(required = false)String datetime,
                                         @RequestParam(required = false)List<String>ids,
                                         @RequestParam(required = false)List<String>collections) throws FactoryException {
        FeatureCollection featureCollection = new FeatureCollection();
        GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory( null );

        Polygon polygonFromCoordinates = geometryFactory.createPolygon(coordinatesFromBbox(bbox));
        polygonFromCoordinates.setSRID(4326);
        List<Feature> features = featureRepository.stacSearch(polygonFromCoordinates,null,datetime,
                ids,collections);
        featureCollection.setFeatures(features);
        featureCollection.setType("FeatureCollection");
        featureCollection.setTimeStamp(new Date());
        featureCollection.setNumberMatched(features.size());
        featureCollection.setNumberReturned(features.size());
        //featureCollection.setLinks(populateFeatureCollectionNextLink(collectionId));
        return featureCollection;

    }

    @PostMapping("/search")
    public FeatureCollection searchByPost(@RequestBody StacSearch stacSearch) throws FactoryException {
        FeatureCollection featureCollection = new FeatureCollection();
        GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory( null );
        Polygon bboxPolygonFromCoordinates=null;
        if(stacSearch.getBbox()!=null) {
            bboxPolygonFromCoordinates = geometryFactory.createPolygon(coordinatesFromBbox(stacSearch.getBbox()));
            bboxPolygonFromCoordinates.setSRID(4326);
        }
        Geometry geomFromGeoJSON=null;
        if(stacSearch.getIntersects()!=null) {
            geomFromGeoJSON = stacSearch.getIntersects();
            geomFromGeoJSON.setSRID(4326);
        }
        List<Feature> features = featureRepository.stacSearch(bboxPolygonFromCoordinates,geomFromGeoJSON,
                stacSearch.getDatetime(),
                stacSearch.getIds(),stacSearch.getCollections());
        featureCollection.setFeatures(features);
        featureCollection.setType("FeatureCollection");
        featureCollection.setTimeStamp(new Date());
        featureCollection.setNumberMatched(features.size());
        featureCollection.setNumberReturned(features.size());
        //featureCollection.setLinks(populateFeatureCollectionNextLink(collectionId));
        return featureCollection;

    }

    private Coordinate[] coordinatesFromBbox(double[] bbox) {
        Coordinate coordinateLL = new CoordinateXY(bbox[0],bbox[1]); //minx, miny
        Coordinate coordinateUL = new CoordinateXY(bbox[0],bbox[3]); //minx, maxy
        Coordinate coordinateUR = new CoordinateXY(bbox[2],bbox[3]); //maxx, maxy
        Coordinate coordinateLR = new CoordinateXY(bbox[2],bbox[1]); //maxx, miny
        return new Coordinate[]{coordinateLL,coordinateUL,coordinateUR,coordinateLR,coordinateLL};
    }
}
