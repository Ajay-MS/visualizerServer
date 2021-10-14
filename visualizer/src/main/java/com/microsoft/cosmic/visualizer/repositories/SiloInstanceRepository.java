
package com.microsoft.cosmic.visualizer.repositories;

import com.microsoft.cosmic.visualizer.dao.Ring;
import com.microsoft.cosmic.visualizer.dao.SiloInstance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiloInstanceRepository extends JpaRepository<SiloInstance, Long> {
    SiloInstance findByNameAndRingIdAndRegionIdAndSiloId(String name, Long ringId, Long regionId, Long siloId);
}
