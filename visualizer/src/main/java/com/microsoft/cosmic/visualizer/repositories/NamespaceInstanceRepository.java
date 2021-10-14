
package com.microsoft.cosmic.visualizer.repositories;

import com.microsoft.cosmic.visualizer.dao.NamespaceInstance;
import com.microsoft.cosmic.visualizer.dao.SiloInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NamespaceInstanceRepository extends JpaRepository<NamespaceInstance, Long> {
    NamespaceInstance findByNid(String nid);

    List<NamespaceInstance> findByNamespaceId(Long namespaceId);

    List<NamespaceInstance> findByNamespaceIdAndRingId(Long namespaceId, Long ringId);
}
