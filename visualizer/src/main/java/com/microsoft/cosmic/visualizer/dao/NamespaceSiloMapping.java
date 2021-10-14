package com.microsoft.cosmic.visualizer.dao;

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
public class NamespaceSiloMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    private Long siloId;

    private Long namespaceId;

    public NamespaceSiloMapping(Long siloId, Long namespaceId) {
        this.siloId = siloId;
        this.namespaceId = namespaceId;
    }
}
