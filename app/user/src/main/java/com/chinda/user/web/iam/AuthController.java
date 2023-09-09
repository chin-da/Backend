package com.chinda.user.web.iam;

import com.chinda.user.application.LoginAndRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final LoginAndRegisterService oAuthService;

    @PostMapping("/login")
    public void login(
            @RequestParam(name = "platform", required = true) final String platform,
            @RequestParam(name = "code", required = true) final String code) {
        try{
            oAuthService.login(platform, code);
            // TODO : 토큰 반환 기능 구현
        } catch (RuntimeException e) {
            // TODO : 회원가입 페이지로 이동
        }
    }
}
