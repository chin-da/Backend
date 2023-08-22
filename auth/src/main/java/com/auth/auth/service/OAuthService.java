package com.auth.auth.service;

import com.auth.auth.client.OAuthClient;
import com.auth.auth.client.OAuthClientProvider;
import com.auth.auth.dto.OauthUserResponse;
import com.core.core.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final OAuthClientProvider oAuthClientProvider;
    private final UserService userService;

    public void login(final String platformName, final String code) {

        OAuthClient oAuthClient = oAuthClientProvider.getClient(platformName);

        String accessToken = oAuthClient.getAccessToken(code);
        OauthUserResponse user = oAuthClient.getUser(accessToken);
        boolean isRegistered = userService.isRegisteredUser(user.getId());

        if (isRegistered) {
            // TODO : 토큰 반환 기능 구현
        } else {
            // TODO : 회원가입 로직 구현
        }
    }

}
