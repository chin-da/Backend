package com.chinda.iam.domain.access;

import com.chinda.iam_shared_kernel.model.Platform;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OauthAgreedUserFactoryProvider {

    private static final Map<String, OAuthAgreedUserFactory> oAuthAgreedUserFactoryMap = new HashMap<>();

    private final KakaoOAuthAgreedUserFactory kakaoOAuthAgreedUserFactory;


    @PostConstruct
    void initializeOAuthClientMap() {
        oAuthAgreedUserFactoryMap.put(Platform.KAKAO.name(), kakaoOAuthAgreedUserFactory);
    }

    public OAuthAgreedUserFactory getClient(final String platformName) {
        if (!oAuthAgreedUserFactoryMap.containsKey(platformName)) {
            throw new UnsupportedOperationException();
        }
        return oAuthAgreedUserFactoryMap.get(platformName);
    }

}
