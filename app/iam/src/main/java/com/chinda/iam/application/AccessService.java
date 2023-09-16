package com.chinda.iam.application;

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
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Optional;

@Service
public class AccessService {

    private final KakaoOAuthAgreedUserFactory kakaoOAuthAgreedUserFactory;
    private final UserRepository userRepository;


    private final RSAPublicKey publicKey;
    private final RSAPrivateKey privateKey;
    private static final Long jwtExpiration = 120 * 60 * 1000L;

    public AccessService(KakaoOAuthAgreedUserFactory kakaoOAuthAgreedUserFactory, UserRepository userRepository, @Value("${jwt.public-key-pem}") String publicKeyPEM, @Value("${jwt.private-key-pem}") String privateKeyPEM) throws NoSuchAlgorithmException, InvalidKeySpecException {
        this.kakaoOAuthAgreedUserFactory = kakaoOAuthAgreedUserFactory;
        this.userRepository = userRepository;
        this.publicKey = getPublicKeyFromPEM(publicKeyPEM);
        this.privateKey = getPrivateKeyFromPEM(privateKeyPEM);
    }


    public AccessToken login(final String platformName, final String authCode) {
        Platform platform = Platform.valueOf(platformName);
        OAuthAgreedUserFactory oAuthAgreedUserFactory = getOAuthAgreedUserFactory(platform);
        OAuthAgreedUser oAuthAgreedUser = oAuthAgreedUserFactory.getOAuthAgreedUser(authCode);

        Optional<User> user = userRepository.findUserBySocialId(oAuthAgreedUser.getSocialId());
        if (user.isPresent()) {
            User registeredUser = user.get();
            return new AccessToken(registeredUser.getId(), jwtExpiration, privateKey);
        } else {
            throw new UserNotRegisteredException();
        }
    }

    public boolean isRegistered(final Long socialId) {
        Optional<User> user = userRepository.findUserBySocialId(socialId);
        return user.isPresent();
    }

    private OAuthAgreedUserFactory getOAuthAgreedUserFactory(final Platform platform) {
        return switch (platform) {
            case KAKAO -> kakaoOAuthAgreedUserFactory;
            case GOOGLE -> throw new UnsupportedOperationException();
        };
    }

    private RSAPublicKey getPublicKeyFromPEM(String publicKeyPEM) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String publicKeyBase64 = publicKeyPEM.replace("-----BEGIN PUBLIC KEY-----", "")
                .replaceAll(System.lineSeparator(), "")
                .replace("-----END PUBLIC KEY-----", "");

        byte[] encoded = Base64.decodeBase64(publicKeyBase64);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encoded);
        return (RSAPublicKey) keyFactory.generatePublic(keySpec);
    }

    private RSAPrivateKey getPrivateKeyFromPEM(String privateKeyPEM) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String privateKeyBase64 = privateKeyPEM.replace("-----BEGIN PRIVATE KEY-----", "")
                .replaceAll(System.lineSeparator(), "")
                .replace("-----END PRIVATE KEY-----", "");

        byte[] encoded = Base64.decodeBase64(privateKeyBase64);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
        return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
    }
}




