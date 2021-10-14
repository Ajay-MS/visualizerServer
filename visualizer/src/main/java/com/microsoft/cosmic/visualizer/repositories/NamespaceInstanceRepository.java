
package com.microsoft.cosmic.visualizer.repositories;

import com.microsoft.cosmic.visualizer.dao.NamespaceInstance;
import com.microsoft.cosmic.visualizer.dao.SiloInstance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NamespaceInstanceRepository extends JpaRepository<NamespaceInstance, Long> {
    NamespaceInstance findByNid(String nid);
}
