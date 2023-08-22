package com.api.api.auth;

import com.auth.auth.service.OAuthService;
import com.auth.auth.service.response.TokenResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final OAuthService oAuthService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(
            @RequestParam(name = "platform", required = true) final String platform,
            @RequestParam(name = "code", required = true) final String code) {
        return ResponseEntity.ok(oAuthService.login(platform, code));
        // TODO : 토큰 반환 기능 구현

    }
}
