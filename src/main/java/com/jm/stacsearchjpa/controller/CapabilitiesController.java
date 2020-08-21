package com.jm.stacsearchjpa.controller;

import com.jm.stacsearchjpa.model.Catalog;
import com.jm.stacsearchjpa.model.Collection;
import com.jm.stacsearchjpa.model.Collections;
import com.jm.stacsearchjpa.model.ConformsTo;
import com.jm.stacsearchjpa.model.Feature;
import com.jm.stacsearchjpa.model.FeatureCollection;
import com.jm.stacsearchjpa.model.FeatureCollectionLink;
import com.jm.stacsearchjpa.model.Headers;
import com.jm.stacsearchjpa.model.Link;
import com.jm.stacsearchjpa.model.repository.CatalogRepository;
import com.jm.stacsearchjpa.model.repository.CollectionRepository;
import com.jm.stacsearchjpa.model.repository.CollectionsRepository;
import com.jm.stacsearchjpa.model.repository.ConformanceRepository;
import com.jm.stacsearchjpa.model.repository.FeatureRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class CapabilitiesController {
    private final CatalogRepository catalogRepository;
    private final CollectionsRepository collectionsRepository;
    private final CollectionRepository collectionRepository;
    private final ConformanceRepository conformanceRepository;
    private final FeatureRepository featureRepository;
    public CapabilitiesController(CatalogRepository catalogRepository, CollectionsRepository collectionsRepository,
                                  CollectionRepository collectionRepository, ConformanceRepository conformanceRepository,
                                  FeatureRepository featureRepository){
        this.catalogRepository=catalogRepository;
        this.collectionsRepository=collectionsRepository;
        this.collectionRepository=collectionRepository;
        this.conformanceRepository=conformanceRepository;
        this.featureRepository=featureRepository;
    }
    @GetMapping("/")
    public Catalog landingPage() {
        return catalogRepository.findFirstByOrderByIdAsc();
    }

    @GetMapping("/conformance")
    public ConformsTo conformance() {
        return conformanceRepository.findFirstByOrderByConformsToAsc();
    }

    @GetMapping("/collections")
    public Collections collections() {
        Collections collections= collectionsRepository.findFirstByOrderByIdAsc();
        return collections;
    }

    @GetMapping("/collections/{collectionId}")
    public Collection collectionById(@PathVariable String collectionId) {
        Optional<Collection> collection= collectionRepository.findById(collectionId);
        return collection.get();
    }

    @GetMapping("/collections/{collectionId}/items")
    public FeatureCollection collectionFeaturesById(@PathVariable String collectionId) {
        List<Feature>features =  featureRepository.findAllByCollection_Id(collectionId);
        FeatureCollection featureCollection = new FeatureCollection();
        featureCollection.setFeatures(features);
        featureCollection.setType("FeatureCollection");
        featureCollection.setTimeStamp(new Date());
        featureCollection.setNumberMatched(features.size());
        featureCollection.setNumberReturned(features.size());
        featureCollection.setLinks(populateFeatureCollectionLinks(collectionId));
        return featureCollection;
    }

    /**
     * Not sure what to do hear, so currently hard coded example
     * Maybe detect host to create self links?
     * @param collectionId
     * @return
     */
    private List<FeatureCollectionLink> populateFeatureCollectionLinks(String collectionId) {
        List<FeatureCollectionLink>out = new ArrayList<>();
        FeatureCollectionLink self = new FeatureCollectionLink();
        self.setHref("http://www.geoserver.example/stac/naip/child/catalog.json");
        self.setRel("child");
        self.setTitle("NAIP Child Catalog");
        self.setLength(0);
        self.setMerge(false);
        self.setMethod("GET");
        Headers headers = new Headers();
        headers.setAccept("application/json");
        self.setHeaders(headers);
        out.add(self);
        return out;
    }


}
