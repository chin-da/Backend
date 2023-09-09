package com.chinda.user.application;

import com.chinda.user.domain.iam.AccessToken;
import com.chinda.user_shared_kernel.model.Platform;
import com.chinda.user.domain.iam.KakaoOAuthRequester;
import com.chinda.user.domain.iam.OAuthAgreedUser;
import com.chinda.user.domain.iam.OAuthRequester;
import com.chinda.user.domain.user.UserRepository;
import com.chinda.user_shared_kernel.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final KakaoOAuthRequester kakaoOAuthRequester;
    private final UserRepository userRepository;

    @Value("${jwt.public-key}")
    private final String publicKey;
    @Value("${jwt.private-key}")
    private final String privateKey;
    private static final Long jwtExpiration = 120 * 60 * 1000L;


    public AccessToken login(final String platformName, final String authCode) {
        Platform platform = Platform.valueOf(platformName);
        OAuthRequester oAuthRequester = getOAuthRequester(platform);
        OAuthAgreedUser oAuthAgreedUser = oAuthRequester.getOAuthAgreedUser(authCode);

        Optional<User> user = userRepository.findUserBySocialId(oAuthAgreedUser.getSocialId());
        if(user.isPresent()) {
            User registeredUser = user.get();
            return new AccessToken(registeredUser.getId(), jwtExpiration, privateKey);
        } else {
            throw new RuntimeException("Not a Registered User");
        }
    }

    private OAuthRequester getOAuthRequester(final Platform platform) {
        return switch (platform) {
            case KAKAO -> kakaoOAuthRequester;
            case GOOGLE -> throw new UnsupportedOperationException();
            default -> throw new RuntimeException("Not a Supported Platform");
        };
    }
    public boolean isRegistered(final Long socialId) {
        Optional<User> user = userRepository.findUserBySocialId(socialId);
        return user.isPresent();
    }




}
