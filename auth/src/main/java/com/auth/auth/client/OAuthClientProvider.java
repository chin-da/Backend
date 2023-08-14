package com.auth.auth.client;

import com.core.core.domain.user.Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OAuthClientProvider {

    private static final Map<Provider, OAuthClient> oAuthClientMap = new HashMap<>();

    private final KakaoOauthClient kakaoOauthClient;

    @PostConstruct
    void initializeOAuthClientMap() {
        oAuthClientMap.put(Provider.KAKAO, kakaoOauthClient);
    }

    public OAuthClient getClient(Provider oAuthProvider) {
        return oAuthClientMap.get(oAuthProvider);
    }
}