package com.microsoft.cosmic.visualizer.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Region {
    private String name;
    private Map<String, Silo> siloMap;

    public Region() {
        siloMap = new HashMap<>();
    }
}
