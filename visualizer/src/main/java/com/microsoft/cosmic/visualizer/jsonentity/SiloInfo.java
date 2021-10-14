package com.microsoft.cosmic.visualizer.jsonentity;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class SiloInfo {
    private String Location;
    private List<SiloInstanceInfo> SiloInstances;
}
