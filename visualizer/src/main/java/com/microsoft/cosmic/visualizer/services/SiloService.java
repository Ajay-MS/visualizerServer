package com.microsoft.cosmic.visualizer.services;

import com.microsoft.cosmic.visualizer.dao.Ring;
import com.microsoft.cosmic.visualizer.dao.Silo;
import com.microsoft.cosmic.visualizer.repositories.RingRepository;
import com.microsoft.cosmic.visualizer.repositories.SiloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiloService {
    @Autowired
    private SiloRepository siloRepository;

    public List<Silo> listAll() {
        return siloRepository.findAll();
    }

    public void save(Silo silo) {
        siloRepository.save(silo);
    }

    public Silo get(Long id) {
        return siloRepository.getById(id);
    }

    public Silo getByName(String name) {
        return siloRepository.findByName(name);
    }
}
