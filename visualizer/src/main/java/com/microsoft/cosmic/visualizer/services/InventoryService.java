package com.microsoft.cosmic.visualizer.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.microsoft.cosmic.visualizer.dao.*;
import com.microsoft.cosmic.visualizer.entity.NamespaceInput;
import com.microsoft.cosmic.visualizer.entity.TopologyNode;
import com.microsoft.cosmic.visualizer.entity.TopologyInput;
import com.microsoft.cosmic.visualizer.jsonentity.NamespaceInstanceMappingInfo;
import com.microsoft.cosmic.visualizer.jsonentity.NamespaceMetadata;
import com.microsoft.cosmic.visualizer.jsonentity.SiloInfo;
import com.microsoft.cosmic.visualizer.jsonentity.SubscriptionInfo;
import com.microsoft.cosmic.visualizer.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    @Autowired
    private NamespaceInstanceRepository namespaceInstanceRepository;

    @Autowired
    private NamespaceRepository namespaceRepository;

    @Autowired
    private NamespaceSiloMappingRepository namespaceSiloMappingRepository;

    @Autowired
    private SiloRepository siloRepository;

    @Autowired
    private SiloInstanceRepository siloInstanceRepository;

    @Value("${visualizer.inventory.basepath}")
    private String griffinRepo;

    private Path inventoryPath;
    private Path tenantsPath;
    private Path infraPath;
    private Path siloPath;

    Gson gson;

    private static final String NAMESPACE_METADATA_FILE = "namespace-metadata.json";
    private static final String SUBSCRIPTION_METADATA_FILE = "subscription-metadata.json";
    private static final String SILO_NAMESPACE_MAP_FILE = "silo-namespaces-map.json";
    private static final String REGION_LOCATION_MAP_FILE = "region-location-map.json";

    private static final String SILO_NAMESPACE_INSTANCE_MAP_FILE = "namespace-instance-map.json";
    private static final String SILO_SILO_METADATA_FILE = "silo-metadata.json";
    private static final String SILO_TOPOLOGY_METADATA_FILE = "topology-metadata.json";

    @PostConstruct
    public void init() {
        inventoryPath = Paths.get(griffinRepo, "sources", "dev", "Inventory");
        tenantsPath = Paths.get(inventoryPath.toString(), "tenants");
        infraPath = Paths.get(inventoryPath.toString(), "infra");
        siloPath = Paths.get(infraPath.toString(), "silos");
        gson = new Gson();
    }



    public NamespaceMetadata getNamespaceMetadataInfo(NamespaceInput namespaceInput) throws IOException {

        String workload = namespaceInput.getWorkload();
        String serviceGroup = namespaceInput.getServiceGroup();
        String namespace = namespaceInput.getNamespaceName();



        Path namespaceMetaPath = Paths.get(tenantsPath.toString(),
                workload, serviceGroup, namespace, NAMESPACE_METADATA_FILE);

        Type type = new TypeToken<NamespaceMetadata>(){}.getType();
        return gson.fromJson(new FileReader(namespaceMetaPath.toString()), type);
    }

    public List<String> getTenants() {


        File folder = new File(tenantsPath.toString());
        return getNames(folder);
    }

    public List<String> getServiceGroups(String workload) {


        Path workloadPath = Paths.get(tenantsPath.toString(), workload);
        File folder = new File(workloadPath.toString());
        return getNames(folder);
    }

    public List<String> getNamespaces(String workload, String serviceGroup) {
        init();

        Path serviceGroupPath = Paths.get(tenantsPath.toString(), workload, serviceGroup);
        File folder = new File(serviceGroupPath.toString());
        return getNames(folder);
    }

    private List<String> getNames(File folder) {
        File[] tenantDirs = folder.listFiles();

        return Arrays.stream(tenantDirs).filter(file ->
                        !file.isFile()).map(file -> file.getName())
                .collect(Collectors.toList());
    }


    public Map<String, List<SubscriptionInfo>> getSubscriptionMetadata() throws IOException {
        Path subscriptionMetaFile = Paths.get(infraPath.toString(), SUBSCRIPTION_METADATA_FILE);
        Type type = new TypeToken<Map<String, List<SubscriptionInfo>>>(){}.getType();
        return gson.fromJson(new FileReader(subscriptionMetaFile.toString()), type);
    }

    public Map<String, List<String>> getSiloNamespaceInfo() throws IOException {
        Path filePath = Paths.get(infraPath.toString(), SILO_NAMESPACE_MAP_FILE);
        Type type = new TypeToken<Map<String, List<String>>>(){}.getType();
        return gson.fromJson(new FileReader(filePath.toString()), type);
    }

    public Map<String, List<String>> getRegionLocationInfo() throws IOException {
        Path filePath = Paths.get(infraPath.toString(), REGION_LOCATION_MAP_FILE);
        Type type = new TypeToken<Map<String, List<String>>>(){}.getType();
        return gson.fromJson(new FileReader(filePath.toString()), type);
    }

    public Map<String, List<NamespaceInstanceMappingInfo>> getSiloNamespaceInstanceMapping(String siloName) throws IOException {
        Path filePath = Paths.get(siloPath.toString(), siloName, SILO_NAMESPACE_INSTANCE_MAP_FILE);
        Type type = new TypeToken<Map<String, List<NamespaceInstanceMappingInfo>>>(){}.getType();
        return gson.fromJson(new FileReader(filePath.toString()), type);
    }

    public Map<String, List<SiloInfo>> getSiloTopologyInfo(String siloName) throws IOException {
        Path filePath = Paths.get(siloPath.toString(), siloName, SILO_TOPOLOGY_METADATA_FILE);
        Type type = new TypeToken<Map<String, List<SiloInfo>>>(){}.getType();
        return gson.fromJson(new FileReader(filePath.toString()), type);
    }


}
