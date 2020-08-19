package com.jm.stacsearchjpa.model.repository;

import com.jm.stacsearchjpa.model.Collections;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionsRepository extends JpaRepository<Collections,Integer> {
    Collections findFirstByOrderByIdAsc();
}
