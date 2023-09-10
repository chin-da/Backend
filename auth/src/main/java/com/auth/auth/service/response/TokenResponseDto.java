package com.auth.auth.service.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenResponseDto {

    private String accessToken;

    public static TokenResponseDto of(
            final String accessToken
    ) {
        return new TokenResponseDto(accessToken);
    }
}
