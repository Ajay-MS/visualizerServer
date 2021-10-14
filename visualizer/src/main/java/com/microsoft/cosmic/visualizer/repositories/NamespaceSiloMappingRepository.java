
package com.microsoft.cosmic.visualizer.repositories;

import com.microsoft.cosmic.visualizer.dao.NamespaceSiloMapping;
import com.microsoft.cosmic.visualizer.dao.SiloInstance;
import com.microsoft.cosmic.visualizer.services.NamespaceSiloMappingService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NamespaceSiloMappingRepository extends JpaRepository<NamespaceSiloMapping, Long> {
    NamespaceSiloMapping findBySiloIdAndNamespaceId(Long siloId, Long namespaceId);
    List<NamespaceSiloMapping> findByNamespaceId(Long namespaceId);
}
