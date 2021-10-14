package com.microsoft.cosmic.visualizer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NamespaceInput {
    private String workload;
    private String serviceGroup;
    private String namespaceName;
}
