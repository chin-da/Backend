package com.chinda.iam.application;

import com.chinda.common.exception.MessageConstants;
import com.chinda.iam.application.exceptions.UserNotRegisteredException;
import com.chinda.iam.domain.access.AccessToken;
import com.chinda.iam.domain.access.OAuthAgreedUser;
import com.chinda.iam.domain.access.OAuthAgreedUserFactory;
import com.chinda.iam.domain.access.OauthAgreedUserFactoryProvider;
import com.chinda.iam.domain.identity.UserRepository;
import com.chinda.iam_shared_kernel.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Service
@RequiredArgsConstructor
public class AccessService {

    private final OauthAgreedUserFactoryProvider oauthAgreedUserFactoryProvider;
    private final UserRepository userRepository;

    // TODO: 환경 변수로 빼기
    private static final Long jwtExpiration = 120 * 60 * 1000L;

    public AccessToken login(final String platformName, final String authCode) throws NoSuchAlgorithmException, InvalidKeySpecException {
        OAuthAgreedUserFactory oAuthAgreedUserFactory = oauthAgreedUserFactoryProvider.getClient(platformName);
        OAuthAgreedUser oAuthAgreedUser = oAuthAgreedUserFactory.getOAuthAgreedUser(authCode);

        User registeredUser = getUser(oAuthAgreedUser.getSocialId());
        return new AccessToken(registeredUser.getId(), jwtExpiration);
    }

    public User getUser(final Long socialId) {
        return userRepository.findUserBySocialId(socialId).orElseThrow(() -> new UserNotRegisteredException(MessageConstants.USER_NOT_FOUND.getMessage()));
    }

}




