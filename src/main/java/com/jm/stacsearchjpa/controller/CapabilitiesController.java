package com.jm.stacsearchjpa.controller;

import com.jm.stacsearchjpa.model.Catalog;
import com.jm.stacsearchjpa.model.Collection;
import com.jm.stacsearchjpa.model.Collections;
import com.jm.stacsearchjpa.model.ConformsTo;
import com.jm.stacsearchjpa.model.repository.CatalogRepository;
import com.jm.stacsearchjpa.model.repository.CollectionRepository;
import com.jm.stacsearchjpa.model.repository.CollectionsRepository;
import com.jm.stacsearchjpa.model.repository.ConformanceRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CapabilitiesController {
    private final CatalogRepository catalogRepository;
    private final CollectionsRepository collectionsRepository;
    private final CollectionRepository collectionRepository;
    private final ConformanceRepository conformanceRepository;
    public CapabilitiesController(CatalogRepository catalogRepository, CollectionsRepository collectionsRepository,
                                  CollectionRepository collectionRepository, ConformanceRepository conformanceRepository){
        this.catalogRepository=catalogRepository;
        this.collectionsRepository=collectionsRepository;
        this.collectionRepository=collectionRepository;
        this.conformanceRepository=conformanceRepository;
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

}
