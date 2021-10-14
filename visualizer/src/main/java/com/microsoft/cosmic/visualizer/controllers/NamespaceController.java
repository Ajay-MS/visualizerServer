package com.microsoft.cosmic.visualizer.controllers;

import com.microsoft.cosmic.visualizer.entity.NamespaceInput;
import com.microsoft.cosmic.visualizer.jsonentity.NamespaceMetadata;
import com.microsoft.cosmic.visualizer.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/namespace")
public class NamespaceController {

    @Autowired
    InventoryService inventoryService;

    @GetMapping
    public String check() {
        return "{}";
    }

    @PostMapping(value = "/info", produces = "application/json; charset=utf-8")
    public NamespaceMetadata getNamespaceInfo(@RequestBody NamespaceInput namespaceInput) throws IOException {
        return inventoryService.getNamespaceMetadataInfo(namespaceInput);
    }


}
