package com.microsoft.cosmic.visualizer.services;

import com.microsoft.cosmic.visualizer.dao.Silo;
import com.microsoft.cosmic.visualizer.dao.SiloInstance;
import com.microsoft.cosmic.visualizer.repositories.SiloInstanceRepository;
import com.microsoft.cosmic.visualizer.repositories.SiloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiloInstanceService {
    @Autowired
    private SiloInstanceRepository siloInstanceRepository;

    public List<SiloInstance> listAll() {
        return siloInstanceRepository.findAll();
    }

    public void save(SiloInstance siloInstance) {
        siloInstanceRepository.save(siloInstance);
    }

    public SiloInstance get(Long id) {
        return siloInstanceRepository.getById(id);
    }

    public SiloInstance findByRingSiloRegion(String name, Long ringId, Long regionId, Long siloId) {
        return siloInstanceRepository.findByNameAndRingIdAndRegionIdAndSiloId(name, ringId, regionId, siloId);
    }
}
