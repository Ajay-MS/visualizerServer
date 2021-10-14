package com.microsoft.cosmic.visualizer.jsonentity;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class NamespaceMetadata {
    private String Name;
    private Map<String, List<NamespaceInfo>> Topology;
}
