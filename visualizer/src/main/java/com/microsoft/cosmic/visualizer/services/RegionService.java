package com.microsoft.cosmic.visualizer.services;

import com.microsoft.cosmic.visualizer.dao.Region;
import com.microsoft.cosmic.visualizer.dao.Ring;
import com.microsoft.cosmic.visualizer.repositories.RegionRepository;
import com.microsoft.cosmic.visualizer.repositories.RingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    public List<Region> listAll() {
        return regionRepository.findAll();
    }

    public void save(Region region) {
        regionRepository.save(region);
    }

    public Region get(Long id) {
        return regionRepository.getById(id);
    }

    public Region getByName(String name) {
        return regionRepository.findByName(name);
    }

    public void cleanup() {
        regionRepository.deleteAll();
    }
}
