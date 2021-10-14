package com.microsoft.cosmic.visualizer.repositories;

import com.microsoft.cosmic.visualizer.dao.Ring;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RingRepository extends JpaRepository<Ring, Long> {
     Ring findByName(String name);
}
