package com.microsoft.cosmic.visualizer.jsonentity;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class SiloInstanceNamespaceInstanceInfo {
    private String Name;
    private List<String> NamespaceInstances;
}
