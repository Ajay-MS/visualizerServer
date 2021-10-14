package com.microsoft.cosmic.visualizer.jsonentity;

import lombok.Data;

import java.util.List;

@Data
public class NamespaceInfo {
    private String Location;
    private List<NamespaceInstanceInfo> Instances;
}
