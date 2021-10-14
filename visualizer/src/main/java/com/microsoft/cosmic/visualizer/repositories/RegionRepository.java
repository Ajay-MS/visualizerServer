package com.microsoft.cosmic.visualizer.repositories;

import com.microsoft.cosmic.visualizer.dao.Region;
import com.microsoft.cosmic.visualizer.dao.Ring;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {
    Region findByName(String name);
}
