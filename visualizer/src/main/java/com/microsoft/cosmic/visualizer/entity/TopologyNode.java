package com.microsoft.cosmic.visualizer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopologyNode {
    private String name;
    private String type;
    private List<TopologyNode> children;

}
