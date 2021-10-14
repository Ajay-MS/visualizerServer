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
@NoArgsConstructor
public class NISIMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    private Long siloInstanceId;

    private String namespaceInstanceId;

    public NISIMapping(Long siloInstanceId, String namespaceInstanceId) {
        this.siloInstanceId = siloInstanceId;
        this.namespaceInstanceId = namespaceInstanceId;
    }
}
