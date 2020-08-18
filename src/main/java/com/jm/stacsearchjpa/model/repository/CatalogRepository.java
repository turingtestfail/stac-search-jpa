package com.jm.stacsearchjpa.model.repository;

import com.jm.stacsearchjpa.model.Catalog;
import org.springframework.data.repository.CrudRepository;

public interface CatalogRepository extends CrudRepository<Catalog,String> {
    Catalog findFirstByOrderByIdAsc(); //The standard says that there is only one top level catalog
}
