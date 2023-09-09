package com.chinda.user.domain.iam;

public interface OAuthRequester {
    OAuthAgreedUser getOAuthAgreedUser(String authCode);

}
