package com.chinda.ranking.domain.excercise;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
public class Exercise {

    @Id
    private String code;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    private float certificateCriterion;

    public boolean needsCertificate(float measurement) {
        return certificateCriterion <= measurement;
    }

    public boolean measurementInValidRange(float measurement) {
        return 0 <= measurement && measurement <= 500;
    }
}
