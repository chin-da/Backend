package com.api.api.auth;

import com.auth.auth.service.OAuthService;
import lombok.RequiredArgsConstructor;
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
    public void login(
            @RequestParam(name = "platform", required = true) final String platform,
            @RequestParam(name = "code", required = true) final String code) {
        oAuthService.login(platform, code);
        // TODO : 토큰 반환 기능 구현
    }
}
