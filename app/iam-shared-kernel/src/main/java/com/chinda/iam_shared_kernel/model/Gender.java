package com.chinda.iam_shared_kernel.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Gender {
    MALE("male", "남자"),
    FEMALE("female", "여자");

    private final String engValue;
    private final String korValue;
}
