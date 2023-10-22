package com.chinda.iam_shared_kernel.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false, unique = true)
    protected Long socialId;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Platform platform;

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