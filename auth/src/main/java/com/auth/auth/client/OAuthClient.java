package com.auth.auth.client;

import com.auth.auth.dto.OauthUserResponse;

public interface OAuthClient {

    String getAccessToken(String authCode);

    OauthUserResponse getUser(String accessToken);
}
