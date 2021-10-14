package com.microsoft.cosmic.visualizer.services;

import com.microsoft.cosmic.visualizer.dao.NamespaceInstance;
import com.microsoft.cosmic.visualizer.dao.SiloInstance;
import com.microsoft.cosmic.visualizer.repositories.NamespaceInstanceRepository;
import com.microsoft.cosmic.visualizer.repositories.NamespaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NamespaceInstanceService {
    @Autowired
    private NamespaceInstanceRepository namesapceRepository;

    public List<NamespaceInstance> listAll() {
        return namesapceRepository.findAll();
    }

    public void save(NamespaceInstance namespaceInstance) {
        namesapceRepository.save(namespaceInstance);
    }

    public NamespaceInstance getByNid(Long nid) {
        return namesapceRepository.findByNid(nid);
    }

    public NamespaceInstance get(Long id) {
        return namesapceRepository.getById(id);
    }
}
