package com.microsoft.cosmic.visualizer.services;

import com.microsoft.cosmic.visualizer.dao.Ring;
import com.microsoft.cosmic.visualizer.repositories.RingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RingService {
    @Autowired
    private RingRepository ringRepository;

    public List<Ring> listAll() {
        return ringRepository.findAll();
    }

    public void save(Ring ring) {
        ringRepository.save(ring);
    }

    public Ring get(Long id) {
        return ringRepository.getById(id);
    }

    public Ring getByName(String ringName) {
        return ringRepository.findByName(ringName);
    }

    public void cleanup() {
        ringRepository.deleteAll();
    }
}
