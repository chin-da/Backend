package com.chinda.user.application;

import com.chinda.user_shared_kernel.model.Platform;
import com.chinda.user.domain.iam.KakaoOAuthRequester;
import com.chinda.user.domain.iam.OAuthAgreedUser;
import com.chinda.user.domain.iam.OAuthRequester;
import com.chinda.user.domain.user.UserRepository;
import com.chinda.user_shared_kernel.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginAndRegisterService {

    private final KakaoOAuthRequester kakaoOAuthRequester;
    private final UserRepository userRepository;

    public void login(final String platformName, final String authCode) {
        Platform platform = Platform.valueOf(platformName);
        OAuthRequester oAuthRequester = getOAuthRequester(platform);
        OAuthAgreedUser oAuthAgreedUser = oAuthRequester.getOAuthAgreedUser(authCode);
        if(isRegistered(oAuthAgreedUser.getSocialId())) {
            // TODO : 토큰 반환 기능 구현
        } else {
            throw new RuntimeException("Not Registered User");
        }
    }

    private OAuthRequester getOAuthRequester(final Platform platform) {
        switch (platform) {
            case KAKAO:
                return kakaoOAuthRequester;
            default:
                throw new RuntimeException("Not Supported Platform");
        }
    }
    public boolean isRegistered(final Long socialId) {
        Optional<User> user = userRepository.findBySocialId(socialId);
        return user.isPresent();
    }




}
