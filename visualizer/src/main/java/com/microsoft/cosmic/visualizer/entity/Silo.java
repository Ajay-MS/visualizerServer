package com.microsoft.cosmic.visualizer.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Silo {
    private String name;
    private List<String> namespaces;
    private Map<String, SiloInstance> siloInstanceMap;

    public Silo() {
        this.namespaces = new ArrayList<>();
        this.siloInstanceMap = new HashMap<>();
    }
}
