package com.auth.auth.service;

import com.auth.auth.client.OAuthClient;
import com.auth.auth.client.OAuthClientProvider;
import com.auth.auth.service.response.TokenResponseDto;
import com.core.core.domain.user.User;
import com.core.core.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final OAuthClientProvider oAuthClientProvider;
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    private static final String TOKEN_PREFIX = "Bearer ";

    public TokenResponseDto login(final String platformName, final String code) {
        OAuthClient oAuthClient = oAuthClientProvider.getClient(platformName);
        Long socialId = getSocialId(oAuthClient, code);

        Optional<User> user = userRepository.findUserBySocialId(socialId);
        if (user.isPresent()) {
            User registeredUser = user.get();
            String token = jwtProvider.issueToken(registeredUser.getId(), registeredUser.getSocialId());
            return TokenResponseDto.of(token);
        }
        // TODO: 회원가입 로직 구현
        return null;
    }

    private Long getSocialId(final OAuthClient oAuthClient, final String code) {
        String accessToken = oAuthClient.getAccessToken(code);
        return oAuthClient.getUser(TOKEN_PREFIX + accessToken).getId();
    }

}
