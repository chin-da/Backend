package com.auth.auth.service;

import com.auth.auth.client.OAuthClient;
import com.auth.auth.client.OAuthClientProvider;
import com.auth.auth.dto.OauthUserResponse;
import com.common.common.exception.ErrorCode;
import com.common.common.exception.model.NotFoundException;
import com.core.core.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final OAuthClientProvider oAuthClientProvider;
    private final UserService userService;

    private static final String TOKEN_PREFIX = "Bearer ";

    public void login(final String platformName, final String code) {

        OAuthClient oAuthClient = oAuthClientProvider.getClient(platformName);

        String accessToken = oAuthClient.getAccessToken(code);
        OauthUserResponse user = oAuthClient.getUser(TOKEN_PREFIX + accessToken);

        boolean isRegistered = userService.isRegisteredUser(user.getId());

        if (!isRegistered) {
            throw new NotFoundException(ErrorCode.UNAUTHORIZED_EXCEPTION);
        }
        // TODO : 토큰 반환 기능 구현
    }

}
