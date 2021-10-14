package com.microsoft.cosmic.visualizer.services;

import com.microsoft.cosmic.visualizer.dao.NISIMapping;
import com.microsoft.cosmic.visualizer.dao.NamespaceSiloMapping;
import com.microsoft.cosmic.visualizer.repositories.NISIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NISIMappingService {
    @Autowired
    private NISIRepository nisiRepository;

    public List<NISIMapping> listAll() {
        return nisiRepository.findAll();
    }

    public void save(NISIMapping siloInstance) {
        nisiRepository.save(siloInstance);
    }

    public NISIMapping get(Long id) {
        return nisiRepository.getById(id);
    }

    public NISIMapping findBySINIMapping(Long siloInstanceId, String namespaceInstanceId) {
        return nisiRepository.findBySiloInstanceIdAndNamespaceInstanceId(siloInstanceId, namespaceInstanceId);
    }

    public NISIMapping findByNamespaceInstanceId(String namespaceInstanceId) {
        return nisiRepository.findByNamespaceInstanceId(namespaceInstanceId);
    }
}
