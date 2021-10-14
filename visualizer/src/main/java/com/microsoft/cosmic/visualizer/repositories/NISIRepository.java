
package com.microsoft.cosmic.visualizer.repositories;

import com.microsoft.cosmic.visualizer.dao.NISIMapping;
import com.microsoft.cosmic.visualizer.dao.NamespaceSiloMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NISIRepository extends JpaRepository<NISIMapping, Long> {
    NISIMapping findBySiloInstanceIdAndNamespaceInstanceId(Long siloInstanceId, String namespaceInstanceId);

    NISIMapping findByNamespaceInstanceId(String namespaceInstanceId);
}
