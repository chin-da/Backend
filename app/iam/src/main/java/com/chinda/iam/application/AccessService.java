package com.chinda.iam.application;

import com.chinda.common.exception.MessageConstants;
import com.chinda.iam.application.exceptions.UserNotRegisteredException;
import com.chinda.iam.domain.access.AccessToken;
import com.chinda.iam.domain.access.KakaoOAuthAgreedUserFactory;
import com.chinda.iam.domain.access.OAuthAgreedUser;
import com.chinda.iam.domain.access.OAuthAgreedUserFactory;
import com.chinda.iam.domain.identity.UserRepository;
import com.chinda.iam_shared_kernel.model.Platform;
import com.chinda.iam_shared_kernel.model.User;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

@Service
public class AccessService {
    private static final String PRIVATE_KEY_PREFIX = "-----BEGIN PRIVATE KEY-----";
    private static final String PRIVATE_KEY_SUFFIX = "-----END PRIVATE KEY-----";
    private static final String EMPTY_STRING = "";

    private final KakaoOAuthAgreedUserFactory kakaoOAuthAgreedUserFactory;
    private final UserRepository userRepository;
    private final RSAPrivateKey privateKey;
    private static final Long jwtExpiration = 120 * 60 * 1000L;

    public AccessService(KakaoOAuthAgreedUserFactory kakaoOAuthAgreedUserFactory, UserRepository userRepository, @Value("${jwt.private-key-pem}") String privateKeyPEM) throws NoSuchAlgorithmException, InvalidKeySpecException {
        this.kakaoOAuthAgreedUserFactory = kakaoOAuthAgreedUserFactory;
        this.userRepository = userRepository;
        this.privateKey = getPrivateKeyFromPEM(privateKeyPEM);
    }


    public AccessToken login(final String platformName, final String authCode) {
        Platform platform = Platform.valueOf(platformName);
        OAuthAgreedUserFactory oAuthAgreedUserFactory = getOAuthAgreedUserFactory(platform);
        OAuthAgreedUser oAuthAgreedUser = oAuthAgreedUserFactory.getOAuthAgreedUser(authCode);

        User registeredUser = getUser(oAuthAgreedUser.getSocialId());
        return new AccessToken(registeredUser.getId(), jwtExpiration, privateKey);
    }

    public User getUser(final Long socialId) {
        return userRepository.findUserBySocialId(socialId).orElseThrow(() -> new UserNotRegisteredException(MessageConstants.USER_NOT_FOUND.getMessage()));
    }

    private OAuthAgreedUserFactory getOAuthAgreedUserFactory(final Platform platform) {
        return switch (platform) {
            case KAKAO -> kakaoOAuthAgreedUserFactory;
            case GOOGLE -> throw new UnsupportedOperationException();
        };
    }

    private RSAPrivateKey getPrivateKeyFromPEM(String privateKeyPEM) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String privateKeyBase64 = privateKeyPEM.replace(PRIVATE_KEY_PREFIX, EMPTY_STRING)
                .replaceAll(System.lineSeparator(), EMPTY_STRING)
                .replace(PRIVATE_KEY_SUFFIX, EMPTY_STRING);

        byte[] encoded = Base64.decodeBase64(privateKeyBase64);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
        return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
    }
}




