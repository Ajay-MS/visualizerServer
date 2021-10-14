package com.microsoft.cosmic.visualizer.repositories;

import com.microsoft.cosmic.visualizer.dao.Namespace;
import com.microsoft.cosmic.visualizer.dao.Ring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NamespaceRepository extends JpaRepository<Namespace, Long> {
     Namespace findByName(String name);
}
