package com.chinda.user.domain.access;

public interface OAuthAgreedUserFactory {
    OAuthAgreedUser getOAuthAgreedUser(String authCode);

}
