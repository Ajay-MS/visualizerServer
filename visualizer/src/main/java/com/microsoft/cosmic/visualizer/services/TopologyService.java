package com.microsoft.cosmic.visualizer.services;

import com.microsoft.cosmic.visualizer.dao.*;
import com.microsoft.cosmic.visualizer.entity.TopologyInput;
import com.microsoft.cosmic.visualizer.entity.TopologyNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TopologyService {

    @Autowired
    NamespaceService namespaceService;

    @Autowired
    NamespaceInstanceService namespaceInstanceService;

    @Autowired
    NISIMappingService nisiMappingService;

    @Autowired
    SiloInstanceService siloInstanceService;

    @Autowired
    RegionService regionService;

    @Autowired
    RingService ringService;

    @Autowired
    SiloService siloService;

    public TopologyNode getRoutingTopology(TopologyInput topologyInput) {

        Ring ring = ringService.getByName(topologyInput.getRing().toLowerCase());

        Namespace namespace = namespaceService.getByName(topologyInput.getNamespaceName());
        List<NamespaceInstance> namespaceInstances = namespaceInstanceService.getByNamespaceIdAndRingId(namespace.getId(), ring.getId());

        Map<String, Map<String, List<SiloInstance>>> map = new HashMap<>();
        for(NamespaceInstance namespaceInstance : namespaceInstances) {
            String partitionName = namespaceInstance.getPName();
            map.putIfAbsent(partitionName, new HashMap<>());
            Map<String, List<SiloInstance>> siloByRegionMap = map.get(partitionName);

            Region region = regionService.get(namespaceInstance.getRegionId());
            siloByRegionMap.putIfAbsent(region.getName(), new ArrayList<>());

            NISIMapping nisiMapping = nisiMappingService.findByNamespaceInstanceId(namespaceInstance.getNid());
            SiloInstance siloInstance = siloInstanceService.get(nisiMapping.getSiloInstanceId());

            siloByRegionMap.get(region.getName()).add(siloInstance);
        }

        return constructTopology(map, ring);
    }

    private TopologyNode constructTopology(Map<String, Map<String, List<SiloInstance>>> map, Ring ring) {

        List<TopologyNode> atms = new ArrayList<>();

        for(Map.Entry<String, Map<String, List<SiloInstance>>> entry: map.entrySet()) {
            String pName = entry.getKey();

            List<TopologyNode> jATMs = new ArrayList<>();
            for(Map.Entry<String, List<SiloInstance>> sEntry : entry.getValue().entrySet()) {
                String regionName = sEntry.getKey();
                String jATMName = pName + "-" + regionName;

                List<TopologyNode> clusters = new ArrayList<>();
                for(SiloInstance instance : sEntry.getValue()) {
//                    Ring ring = ringService.get(instance.getRingId());
                    Silo silo = siloService.get(instance.getSiloId());

                    String clusterName = String.format("%s-%s-%s-%s", ring.getName(), silo.getName(), instance.getName(), regionName);
                    clusters.add(new TopologyNode(clusterName, "cluster", null));
                }

                jATMs.add(new TopologyNode(jATMName, "atm", clusters));
            }

            atms.add(new TopologyNode(pName, "atm", jATMs));
        }

        return new TopologyNode("DNS", "dns", atms);
    }

}
