package com.chinda.user.application;

import com.chinda.user.domain.iam.AccessToken;
import com.chinda.user_shared_kernel.model.Platform;
import com.chinda.user.domain.iam.KakaoOAuthRequester;
import com.chinda.user.domain.iam.OAuthAgreedUser;
import com.chinda.user.domain.iam.OAuthRequester;
import com.chinda.user.domain.user.UserRepository;
import com.chinda.user_shared_kernel.model.User;
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
public class AccountService {

    private final KakaoOAuthRequester kakaoOAuthRequester;
    private final UserRepository userRepository;


    private final RSAPublicKey publicKey;
    private final RSAPrivateKey privateKey;
    private static final Long jwtExpiration = 120 * 60 * 1000L;

    public AccountService(KakaoOAuthRequester kakaoOAuthRequester, UserRepository userRepository, @Value("${jwt.public-key-pem}") String publicKeyPEM, @Value("${jwt.private-key-pem}") String privateKeyPEM) throws NoSuchAlgorithmException, InvalidKeySpecException {
        this.kakaoOAuthRequester = kakaoOAuthRequester;
        this.userRepository = userRepository;
        this.publicKey = getPublicKeyFromPEM(publicKeyPEM);
        this.privateKey = getPrivateKeyFromPEM(privateKeyPEM);
    }


    public AccessToken login(final String platformName, final String authCode) {
        Platform platform = Platform.valueOf(platformName);
        OAuthRequester oAuthRequester = getOAuthRequester(platform);
        OAuthAgreedUser oAuthAgreedUser = oAuthRequester.getOAuthAgreedUser(authCode);

        Optional<User> user = userRepository.findUserBySocialId(oAuthAgreedUser.getSocialId());
        if (user.isPresent()) {
            User registeredUser = user.get();
            return new AccessToken(registeredUser.getId(), jwtExpiration, privateKey);
        } else {
            throw new RuntimeException("Not a Registered User");
        }
    }

    public boolean isRegistered(final Long socialId) {
        Optional<User> user = userRepository.findUserBySocialId(socialId);
        return user.isPresent();
    }

    private OAuthRequester getOAuthRequester(final Platform platform) {
        return switch (platform) {
            case KAKAO -> kakaoOAuthRequester;
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




