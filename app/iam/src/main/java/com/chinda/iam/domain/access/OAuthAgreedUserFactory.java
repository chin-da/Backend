package com.chinda.iam.domain.access;

public interface OAuthAgreedUserFactory {
    OAuthAgreedUser getOAuthAgreedUser(String authCode);

}
