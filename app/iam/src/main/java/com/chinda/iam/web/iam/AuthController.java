package com.chinda.iam.web.iam;

import com.chinda.iam.application.AccessService;
import com.chinda.iam.domain.access.AccessToken;
import com.chinda.iam.web.iam.dto.request.LoginVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AccessService accessService;

    @PostMapping("/login")
    public ResponseEntity<AccessToken> login(
            @Valid @RequestBody final LoginVo loginVo) {
        return ResponseEntity.ok(accessService.login(loginVo.getPlatformName(), loginVo.getAuthCode()));
        // TODO : 토큰 반환 기능 구현

    }
}
