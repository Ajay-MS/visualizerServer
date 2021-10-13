package com.microsoft.cosmic.visualizer.services;

import com.microsoft.cosmic.visualizer.entity.NamespaceInput;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    @Value("${visualizer.inventory.basepath}")
    private String inventoryRepo;

    private Path tenantsInventoryPath;

    private static final String NAMESPACE_METADATA_FILE = "namespace-metadata.json";

    public void init() {
        tenantsInventoryPath = Paths.get(inventoryRepo, "sources", "dev", "Inventory", "tenants");
    }

    public String getInventoryMetaInfo(NamespaceInput namespaceInput) throws IOException {

        init();

        String workload = namespaceInput.getWorkload();
        String serviceGroup = namespaceInput.getServiceGroup();
        String namespace = namespaceInput.getNamespaceName();

        Path inventoryPath = Paths.get(tenantsInventoryPath.toString(),
                workload, serviceGroup, namespace, NAMESPACE_METADATA_FILE);

        System.out.println(inventoryPath.toString());

        String content = Files.readString(inventoryPath, StandardCharsets.US_ASCII);

        return content;
    }

    public List<String> getTenants() {
        init();

        File folder = new File(tenantsInventoryPath.toString());
        return getNames(folder);
    }

    public List<String> getServiceGroups(String workload) {
        init();

        Path workloadPath = Paths.get(tenantsInventoryPath.toString(), workload);
        File folder = new File(workloadPath.toString());
        return getNames(folder);
    }

    public List<String> getNamespaces(String workload, String serviceGroup) {
        init();

        Path serviceGroupPath = Paths.get(tenantsInventoryPath.toString(), workload, serviceGroup);
        File folder = new File(serviceGroupPath.toString());
        return getNames(folder);
    }

    private List<String> getNames(File folder) {
        File[] tenantDirs = folder.listFiles();

        return Arrays.stream(tenantDirs).filter(file ->
                        !file.isFile()).map(file -> file.getName())
                .collect(Collectors.toList());
    }

}
