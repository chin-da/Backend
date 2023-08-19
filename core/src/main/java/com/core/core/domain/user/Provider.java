package com.core.core.domain.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Provider {
    NAVER("naver"),
    KAKAO("kakao");

    private final String providerName;
}
