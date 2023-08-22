package com.auth.auth.client;

import com.core.core.domain.user.Platform;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OAuthClientProvider {

    private static final Map<Platform, OAuthClient> oAuthClientMap = new HashMap<>();

    private final KakaoOauthClient kakaoOauthClient;

    @PostConstruct
    void initializeOAuthClientMap() {
        oAuthClientMap.put(Platform.KAKAO, kakaoOauthClient);
    }

    public OAuthClient getClient(String platformName) {
        Platform platform = Platform.valueOf(platformName);
        return oAuthClientMap.get(platform);
    }
}