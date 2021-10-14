package com.microsoft.cosmic.visualizer.services;

import com.microsoft.cosmic.visualizer.dao.NamespaceSiloMapping;
import com.microsoft.cosmic.visualizer.dao.SiloInstance;
import com.microsoft.cosmic.visualizer.repositories.NamespaceSiloMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NamespaceSiloMappingService {
    @Autowired
    private NamespaceSiloMappingRepository namespaceSiloMappingRepository;

    public List<NamespaceSiloMapping> listAll() {
        return namespaceSiloMappingRepository.findAll();
    }

    public void save(NamespaceSiloMapping siloInstance) {
        namespaceSiloMappingRepository.save(siloInstance);
    }

    public NamespaceSiloMapping get(Long id) {
        return namespaceSiloMappingRepository.getById(id);
    }

    public NamespaceSiloMapping findBySiloNamespace(Long siloId, Long namespaceId) {
        return namespaceSiloMappingRepository.findBySiloIdAndNamespaceId(siloId, namespaceId);
    }
}
