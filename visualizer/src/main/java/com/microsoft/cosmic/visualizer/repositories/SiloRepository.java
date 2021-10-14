package com.microsoft.cosmic.visualizer.repositories;

import com.microsoft.cosmic.visualizer.dao.Ring;
import com.microsoft.cosmic.visualizer.dao.Silo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiloRepository extends JpaRepository<Silo, Long> {
    Silo findByName(String name);
}
