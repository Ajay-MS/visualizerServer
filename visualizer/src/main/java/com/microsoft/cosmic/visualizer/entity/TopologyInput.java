package com.microsoft.cosmic.visualizer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopologyInput {
    private String workload;
    private String serviceGroup;
    private String namespaceName;
    private String ring;
}
