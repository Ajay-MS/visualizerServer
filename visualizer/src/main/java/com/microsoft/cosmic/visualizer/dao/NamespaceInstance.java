package com.microsoft.cosmic.visualizer.dao;

import lombok.AllArgsConstructor;
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
public class NamespaceInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    private String nid;

    private String pName;
    private String sName;

    private Long namespaceId;
    private Long regionId;

    private Long ringId;

    public NamespaceInstance(String id, String partition, String nameSuffix, Long namespaceId, Long regionId, Long ringId) {
        this.nid = id;
        this.pName = partition;
        this.sName = nameSuffix;
        this.namespaceId = namespaceId;
        this.regionId = regionId;
        this.ringId = ringId;
    }
}
