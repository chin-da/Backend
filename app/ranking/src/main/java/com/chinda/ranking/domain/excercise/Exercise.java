package com.chinda.ranking.domain.excercise;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
public class Excercise {

    @Id
    private String code;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    private float certificateCriterion;
    
}
