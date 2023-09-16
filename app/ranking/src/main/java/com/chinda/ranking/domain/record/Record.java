package com.chinda.ranking.domain.record;

import com.chinda.common.model.TimestampedEntity;
import com.chinda.ranking.domain.excercise.Exercise;
import com.chinda.user_shared_kernel.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
public class Record extends TimestampedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @Column(nullable = false)
    private Integer measurement;

    @OneToOne
    @JoinColumn(name = "certificate_id")
    private Certificate certificate;
}
