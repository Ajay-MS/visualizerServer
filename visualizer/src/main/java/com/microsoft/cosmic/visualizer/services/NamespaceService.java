package com.microsoft.cosmic.visualizer.services;

import com.microsoft.cosmic.visualizer.dao.Namespace;
import com.microsoft.cosmic.visualizer.dao.Ring;
import com.microsoft.cosmic.visualizer.repositories.NamespaceRepository;
import com.microsoft.cosmic.visualizer.repositories.RingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NamespaceService {
    @Autowired
    private NamespaceRepository namespaceRepository;

    public List<Namespace> listAll() {
        return namespaceRepository.findAll();
    }

    public Namespace save(Namespace ring) {
        return namespaceRepository.save(ring);
    }

    public Namespace get(Long id) {
        return namespaceRepository.getById(id);
    }

    public Namespace getByName(String namespaceName) {
        return namespaceRepository.findByName(namespaceName);
    }

    public void cleanup() {
        namespaceRepository.deleteAll();
    }
}
