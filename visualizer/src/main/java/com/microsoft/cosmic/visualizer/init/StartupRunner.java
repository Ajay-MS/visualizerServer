package com.microsoft.cosmic.visualizer.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    @Autowired
    DataPopulationService dataPopulationService;

    @Override
    public void run(String... args) throws Exception {
        dataPopulationService.populateRings();
        dataPopulationService.populateRegions();
        dataPopulationService.populateSilos();
        dataPopulationService.populateSiloInstances();
        dataPopulationService.populateNamespacesAndSiloMapping();
        dataPopulationService.populateNamespaceInstances();
        dataPopulationService.populateNISIMapping();
    }
}
