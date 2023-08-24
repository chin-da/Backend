package com.auth.auth.client;

import com.auth.auth.dto.KakaoTokenResponse;
import com.auth.auth.dto.KakaoUserResponse;
import com.auth.auth.dto.OauthUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@RequiredArgsConstructor
public class KakaoOauthClient implements OAuthClient {


    private final KakaoApiClient kakaoApiClient;
    private final KakaoAuthApiClient kakaoAuthApiClient;

    private static final String GRANT_TYPE = "authorization_code";
    private static final String KAKAO_API_URI = "https://kapi.kakao.com";
    private static final String KAKAO_AUTH_API_URI = "https://kauth.kakao.com";

    @Value("${oauth.kakao.client-id}")
    private String clientId;

    @Value("${oauth.kakao.redirect-uri}")
    private String redirectUri;

    @Override
    public String getAccessToken(String authCode) {
        return kakaoAuthApiClient.getAccessToken(GRANT_TYPE, clientId, redirectUri, authCode).getAccessToken();
    }

    @Override
    public OauthUserResponse getUser(String accessToken) {
        return kakaoApiClient.getUser(accessToken);
    }

    @FeignClient(name = "kakaoApiClient", url = KAKAO_API_URI)
    public interface KakaoApiClient {
        @GetMapping(value = "/v2/user/me")
        KakaoUserResponse getUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken);
    }

    @FeignClient(name = "kakaoAuthApiClient", url = KAKAO_AUTH_API_URI)
    public interface KakaoAuthApiClient {
        @PostMapping(value = "/oauth/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
        KakaoTokenResponse getAccessToken(
                @RequestParam("grant_type") String grantType,
                @RequestParam("client_id") String clientId,
                @RequestParam("redirect_uri") String redirectUri,
                @RequestParam("code") String code
        );
    }
}