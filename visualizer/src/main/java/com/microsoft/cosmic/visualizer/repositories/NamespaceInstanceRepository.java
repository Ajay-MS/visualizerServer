
package com.microsoft.cosmic.visualizer.repositories;

import com.microsoft.cosmic.visualizer.dao.NamespaceInstance;
import com.microsoft.cosmic.visualizer.dao.SiloInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NamespaceInstanceRepository extends JpaRepository<NamespaceInstance, Long> {
    NamespaceInstance findByNid(Long nid);
}
