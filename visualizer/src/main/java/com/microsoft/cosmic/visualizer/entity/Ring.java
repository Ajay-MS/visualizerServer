package com.microsoft.cosmic.visualizer.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Ring {

    private String name;
    private Map<String, Region> regionMap;

    public Ring() {
        regionMap =  new HashMap<>();
    }
}
