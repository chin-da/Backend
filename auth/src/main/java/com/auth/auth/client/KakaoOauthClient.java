package com.auth.auth.client;


import com.auth.auth.dto.KakaoTokenResponse;
import com.auth.auth.dto.KakaoUserResponse;
import com.auth.auth.dto.OauthUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Component
@RequiredArgsConstructor
public class KakaoOauthClient implements OAuthClient {
    public static final String KAKAO_ACCESS_TOKEN_URI = "https://kauth.kakao.com/oauth/token";
    public static final String KAKAO_USER_INFO_URI = "https://kapi.kakao.com/v2/user/me";

    @Value("${oauth.kakao.client-id}")
    private String clientId;

    @Value("${oauth.kakao.redirect-uri}")
    private String redirectUri;

    private RestTemplate restTemplate;

    public static final String GRANT_TYPE = "authorization_code";

    @Override
    public String getAccessToken(String authCode) {
        MultiValueMap<String, String> body = createRequestBody(authCode);
        HttpHeaders header = createRequestHeader();

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, header);

        restTemplate = new RestTemplate();

        ResponseEntity<KakaoTokenResponse> response = restTemplate.exchange(
                KAKAO_ACCESS_TOKEN_URI,
                POST,
                request,
                KakaoTokenResponse.class
        );

        return response.getBody().getAccessToken();
    }

    @Override
    public OauthUserResponse getUser(String accessToken) {
        HttpEntity<HttpHeaders> request = createRequest(accessToken);

        restTemplate = new RestTemplate();

        try {
            ResponseEntity<KakaoUserResponse> response = restTemplate.exchange(
                    KAKAO_USER_INFO_URI,
                    GET,
                    request,
                    KakaoUserResponse.class
            );
            return response.getBody();
        } catch (Exception e) { // TODO : 커스텀 exception 으로 바꾸기
            throw new IllegalArgumentException("정보를 가져오는 데 실패했습니다.");
        }

    }

    MultiValueMap<String, String> createRequestBody(String authCode) {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", GRANT_TYPE);
        body.add("client_id", clientId);
        body.add("redirect_uri", redirectUri);
        body.add("code", authCode);
        return new LinkedMultiValueMap<>(body);
    }

    HttpHeaders createRequestHeader() {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return new HttpHeaders(header);
    }

    private HttpEntity<HttpHeaders> createRequest(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        return new HttpEntity<>(headers);
    }
}