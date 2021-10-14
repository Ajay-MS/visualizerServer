package com.microsoft.cosmic.visualizer.services;


import com.microsoft.cosmic.visualizer.entity.Ring;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InfraService {

    private Map<String, Ring> ringMap;

    private InfraService() {
        ringMap = new HashMap<>();
    }

}
