package com.jm.stacsearchjpa.controller;

import com.jm.stacsearchjpa.model.Catalog;
import com.jm.stacsearchjpa.model.Collections;
import com.jm.stacsearchjpa.model.repository.CatalogRepository;
import com.jm.stacsearchjpa.model.repository.CollectionsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CapabilitiesController {
    private final CatalogRepository catalogRepository;
    private final CollectionsRepository collectionsRepository;
    public CapabilitiesController(CatalogRepository catalogRepository, CollectionsRepository collectionsRepository){
        this.catalogRepository=catalogRepository;
        this.collectionsRepository=collectionsRepository;
    }
    @GetMapping("/")
    public Catalog landingPage() {
        return catalogRepository.findFirstByOrderByIdAsc();
    }

    @GetMapping("/collections")
    public Collections collections() {
        Collections collections= collectionsRepository.findFirstByOrderByIdAsc();
        return collections;
    }

}
