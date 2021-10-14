package com.microsoft.cosmic.visualizer.init;

import com.microsoft.cosmic.visualizer.dao.*;
import com.microsoft.cosmic.visualizer.entity.NamespaceInput;
import com.microsoft.cosmic.visualizer.jsonentity.*;
import com.microsoft.cosmic.visualizer.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class DataPopulationService {

    @Autowired
    InventoryService inventoryService;

    @Autowired
    RingService ringService;

    @Autowired
    RegionService regionService;

    @Autowired
    SiloService siloService;

    @Autowired
    NamespaceService namespaceService;

    @Autowired
    SiloInstanceService siloInstanceService;

    @Autowired
    NamespaceSiloMappingService namespaceSiloMappingService;

    @Autowired
    NamespaceInstanceService namespaceInstanceService;

    @Autowired
    NISIMappingService nisiMappingService;

    public void populateRings() throws IOException {

        Map<String, List<SubscriptionInfo>> subInfo = inventoryService.getSubscriptionMetadata();

        subInfo.keySet()
                .stream()
                .map(ringName -> new Ring(ringName.toLowerCase()))
                .forEach(ring -> {
                    if (ringService.getByName(ring.getName()) == null)
                        ringService.save(ring);
                });
    }

    public void populateRegions() throws IOException {
        Map<String, List<String>> regionLocationInfo = inventoryService.getRegionLocationInfo();

        for(List<String> regions: regionLocationInfo.values()) {
            regions
                    .stream()
                    .map(region -> new Region(region.toLowerCase()))
                    .forEach(region -> {
                        if (regionService.getByName(region.getName()) == null)
                            regionService.save(region);
                    });
        }
    }

    public void populateSilos() throws IOException {
        Map<String, List<String>> siloNamespaceInfo = inventoryService.getSiloNamespaceInfo();

        siloNamespaceInfo.keySet()
                .stream()
                .map(siloName -> new Silo(siloName.toLowerCase()))
                .forEach(silo -> {
                    if (siloService.getByName(silo.getName()) == null)
                        siloService.save(silo);
                });

    }

    public void populateSiloInstances() throws IOException {
        Map<String, List<String>> siloNamespaceInfo = inventoryService.getSiloNamespaceInfo();

        Set<String> siloNames = siloNamespaceInfo.keySet();

        for(String siloName : siloNames) {
            Map<String, List<SiloInfo>> siloInfoMap = inventoryService.getSiloTopologyInfo(siloName);

            Silo silo = siloService.getByName(siloName.toLowerCase());
            if (silo == null) {
                System.out.println("Silo not found: " + siloName);
            }

            for(Map.Entry<String, List<SiloInfo>> entry: siloInfoMap.entrySet()) {

                String ringName = entry.getKey().toLowerCase();
                Ring ring = ringService.getByName(ringName);

                for(SiloInfo siloInfo: entry.getValue()) {
                    String locationName = siloInfo.getLocation().toLowerCase();
                    Region region = regionService.getByName(locationName);

                    for(SiloInstanceInfo siloInstanceInfo: siloInfo.getSiloInstances()) {

                        String siloInstanceName = siloInstanceInfo.getName().toLowerCase();

                        SiloInstance siloInstance = siloInstanceService.findByRingSiloRegion(siloInstanceName, ring.getId(), region.getId(), silo.getId());
                        if (siloInstance == null) {
                            siloInstanceService.save(new SiloInstance(siloInstanceName, ring.getId(), region.getId(), silo.getId()));
                        }
                    }
                }

            }
        }

        siloNamespaceInfo.keySet()
                .stream()
                .map(siloName -> new Silo(siloName.toLowerCase()))
                .forEach(silo -> {
                    if (siloService.getByName(silo.getName()) == null)
                        siloService.save(silo);
                });

    }

    public void populateNamespacesAndSiloMapping() throws IOException {
        Map<String, List<String>> siloNamespaceInfo = inventoryService.getSiloNamespaceInfo();

        for(Map.Entry<String, List<String>> entry: siloNamespaceInfo.entrySet()) {
            String siloName = entry.getKey().toLowerCase();

            Silo silo = siloService.getByName(siloName);

            List<String> namespaces = entry.getValue();

            namespaces
                    .stream()
                    .map(namespace -> namespace.toLowerCase())
                    .forEach(namespaceName -> {
                        Namespace namespace = namespaceService.getByName(namespaceName);
                        if (namespace == null) {
                            namespace = namespaceService.save(new Namespace(namespaceName));
                        }

                        NamespaceSiloMapping mapping = namespaceSiloMappingService.findBySiloNamespace(silo.getId(), namespace.getId());
                        if (mapping == null) {
                            namespaceSiloMappingService.save(new NamespaceSiloMapping(silo.getId(), namespace.getId()));
                        }
                    });
        }

    }

    public void populateNamespaceInstances() throws IOException {
        List<String> workloads = inventoryService.getTenants();

        for(String workload : workloads) {
            List<String> serviceGroups = inventoryService.getServiceGroups(workload);
            for(String serviceGroup : serviceGroups) {

                if (serviceGroup.contains("policies"))
                    continue;

                List<String> namespaces = inventoryService.getNamespaces(workload, serviceGroup);



                for(String namespaceName : namespaces) {
                    NamespaceMetadata namespaceMetadata = inventoryService.getNamespaceMetadataInfo(new NamespaceInput(workload, serviceGroup, namespaceName));
                    Namespace namespace = namespaceService.getByName(namespaceName);

                    if (namespaceMetadata.getTopology() == null)
                        continue;

                    for(Map.Entry<String, List<NamespaceInfo>> entry : namespaceMetadata.getTopology().entrySet()) {
                        String ringName = entry.getKey().toLowerCase();
                        Ring ring = ringService.getByName(ringName);

                        for(NamespaceInfo namespaceInfo : entry.getValue()) {
                            String regionName = namespaceInfo.getLocation().toLowerCase();
                            Region region = regionService.getByName(regionName);

                            for(NamespaceInstanceInfo instanceInfo : namespaceInfo.getInstances()) {

                                 String namespaceId = instanceInfo.getId();

                                 NamespaceInstance ni = namespaceInstanceService.getByNid(namespaceId);
                                 if (ni == null) {
                                     ni = new NamespaceInstance(namespaceId, instanceInfo.getPartition(), instanceInfo.getNameSuffix(), namespace.getId(), region.getId(), ring.getId());
                                     namespaceInstanceService.save(ni);
                                 }

                            }
                        }
                    }
                }
            }
        }
    }

    public void populateNISIMapping() throws IOException {
        Map<String, List<String>> siloNamespaceInfo = inventoryService.getSiloNamespaceInfo();

        Set<String> siloNames = siloNamespaceInfo.keySet();

        for(String siloName : siloNames) {
            Silo silo = siloService.getByName(siloName.toLowerCase());

            Map<String, List<NamespaceInstanceMappingInfo>> nimappingInfo = inventoryService.getSiloNamespaceInstanceMapping(siloName);

            for(Map.Entry<String, List<NamespaceInstanceMappingInfo>> entry : nimappingInfo.entrySet()) {
                String ringName = entry.getKey().toLowerCase();
                Ring ring = ringService.getByName(ringName);

                for(NamespaceInstanceMappingInfo niMappingInfo : entry.getValue()) {

                    String regionName = niMappingInfo.getLocation().toLowerCase();
                    Region region = regionService.getByName(regionName);


                    for(SiloInstanceNamespaceInstanceInfo siniInfo : niMappingInfo.getSiloInstances()) {

                        String siName = siniInfo.getName().toLowerCase();
                        SiloInstance si = siloInstanceService.findByRingSiloRegion(siName, ring.getId(), region.getId(), silo.getId());

                        for(String namespaceId : siniInfo.getNamespaceInstances()) {
                           NamespaceInstance ni =  namespaceInstanceService.getByNid(namespaceId);

                            NISIMapping nisimapping = nisiMappingService.findBySINIMapping(si.getId(), ni.getNid());
                            if (nisimapping == null) {
                                nisimapping =  new NISIMapping(si.getId(), ni.getNid());
                                nisiMappingService.save(nisimapping);
                            }
                        }
                    }

                }
            }
        }
    }

}
