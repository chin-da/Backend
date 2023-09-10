package com.api.api.auth;

import com.api.api.auth.dto.request.LoginVo;
import com.auth.auth.service.OAuthService;
import com.auth.auth.service.response.TokenResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final OAuthService oAuthService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(
            @Valid @RequestBody final LoginVo loginVo) {
        return ResponseEntity.ok(oAuthService.login(loginVo.getPlatformName(), loginVo.getAuthCode()));
        // TODO : 토큰 반환 기능 구현

    }
}
