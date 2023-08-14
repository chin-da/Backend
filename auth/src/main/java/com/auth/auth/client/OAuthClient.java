package com.auth.auth.client;

import com.auth.auth.dto.OauthUserResponse;

public interface OAuthClient {

    public String getAccessToken(String authCode);

    public OauthUserResponse getUser(String accessToken);
}
