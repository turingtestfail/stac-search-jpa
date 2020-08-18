package com.jm.stacsearchjpa.controller;

import com.jm.stacsearchjpa.model.Catalog;
import com.jm.stacsearchjpa.model.repository.CatalogRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CapabilitiesController {
    private final CatalogRepository catalogRepository;
    public CapabilitiesController(CatalogRepository catalogRepository){
        this.catalogRepository=catalogRepository;
    }
    @GetMapping("/")
    public Catalog landingPage() {
        return catalogRepository.findFirstByOrderByIdAsc();
    }

}
