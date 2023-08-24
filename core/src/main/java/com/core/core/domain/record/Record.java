package com.core.core.domain.record;

import com.core.core.domain.exercise.Exercise;
import com.core.core.domain.user.User;
import com.core.core.global.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Record extends BaseEntity {

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
