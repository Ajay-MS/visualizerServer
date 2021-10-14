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
public class Silo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    private String name;

    public Silo(String name) {
        this.name = name;
    }
}
