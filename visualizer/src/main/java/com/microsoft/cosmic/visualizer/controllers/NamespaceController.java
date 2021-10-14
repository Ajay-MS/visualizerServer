package com.microsoft.cosmic.visualizer.controllers;

import com.microsoft.cosmic.visualizer.entity.NamespaceInput;
import com.microsoft.cosmic.visualizer.entity.TopologyNode;
import com.microsoft.cosmic.visualizer.entity.TopologyInput;
import com.microsoft.cosmic.visualizer.jsonentity.NamespaceMetadata;
import com.microsoft.cosmic.visualizer.services.InventoryService;
import com.microsoft.cosmic.visualizer.services.TopologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/namespace")
public class NamespaceController {

    @Autowired
    InventoryService inventoryService;

    @Autowired
    TopologyService topologyService;

    @GetMapping
    public String check() {
        return "{}";
    }

    @PostMapping(value = "/info", produces = "application/json; charset=utf-8")
    public NamespaceMetadata getNamespaceInfo(@RequestBody NamespaceInput namespaceInput) throws IOException {
        return inventoryService.getNamespaceMetadataInfo(namespaceInput);
    }


    @PostMapping(value = "/routing", produces = "application/json; charset=utf-8")
    public TopologyNode getNamespaceInfo(@RequestBody TopologyInput topologyInput) throws IOException {
        return topologyService.getRoutingTopology(topologyInput);
    }


}
