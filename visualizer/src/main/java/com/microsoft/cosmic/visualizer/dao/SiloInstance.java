package com.microsoft.cosmic.visualizer.dao;

import com.microsoft.cosmic.visualizer.services.SiloInstanceService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class SiloInstance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    private Long ringId;
    private Long regionId;
    private Long siloId;

    private String name;

    public SiloInstance(String name, Long ringId, Long regionId, Long siloId) {
        this.name = name;
        this.ringId = ringId;
        this.regionId = regionId;
        this.siloId = siloId;
    }
}
