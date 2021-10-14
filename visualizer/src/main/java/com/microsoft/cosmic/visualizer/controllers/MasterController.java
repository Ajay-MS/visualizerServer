package com.microsoft.cosmic.visualizer.controllers;

import com.microsoft.cosmic.visualizer.entity.NamespaceInput;
import com.microsoft.cosmic.visualizer.services.InventoryService;
import com.microsoft.cosmic.visualizer.services.RingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/master")
public class MasterController {

    @Autowired
    InventoryService inventoryService;

    @Autowired
    RingService ringService;

    @GetMapping("/workloads")
    public List<String> getTenants() {
        return inventoryService.getTenants();
    }

    @GetMapping("/serviceGroups")
    public List<String> getServiceGroups(@RequestParam("workload") String workload) {
        return inventoryService.getServiceGroups(workload);
    }

    @GetMapping("/namespaces")
    public List<String> getNamespaces(@RequestParam("workload") String workload, @RequestParam("serviceGroup") String serviceGroup) {
       return inventoryService.getNamespaces(workload, serviceGroup);
    }

    @GetMapping("/rings")
    public List<String> getRings() throws IOException {
        return ringService.listAll().stream().map(ring -> ring.getName()).collect(Collectors.toList());
    }
}
