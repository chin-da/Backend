package com.chinda.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@NoArgsConstructor
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false, unique = true)
    protected Long socialId;

    @Column(nullable = false)
    protected Platform platform;

    @Column(nullable = false, unique = true)
    protected String nickname;

    @Column(nullable = false)
    protected int age;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected Gender gender;

    @Column(nullable = false)
    protected Double height;

    @Column(nullable = false)
    protected Double weight;
}
