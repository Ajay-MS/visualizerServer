package com.microsoft.cosmic.visualizer.entity;

import lombok.Data;

@Data
public class NamespaceInput {
    private String workload;
    private String serviceGroup;
    private String namespaceName;


    private String contentType;
}
