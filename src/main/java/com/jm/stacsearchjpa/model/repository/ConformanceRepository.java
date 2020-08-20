package com.jm.stacsearchjpa.model.repository;

import com.jm.stacsearchjpa.model.ConformsTo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConformanceRepository extends JpaRepository<ConformsTo, List<String>> {
    ConformsTo findFirstByOrderByConformsToAsc();
}
