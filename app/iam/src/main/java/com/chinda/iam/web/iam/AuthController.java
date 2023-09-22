package com.chinda.iam.web.iam;

import com.chinda.iam.application.AccessService;
import com.chinda.iam.application.IdentityService;
import com.chinda.iam.domain.access.AccessToken;
import com.chinda.iam.web.iam.dto.request.LoginVo;
import com.chinda.iam.web.iam.dto.request.NicknameValidationRequestDto;
import com.chinda.iam.web.iam.dto.request.SignUpRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AccessService accessService;
    private final IdentityService identityService;

    @PostMapping("/login")
    public ResponseEntity<AccessToken> login(
            @Valid @RequestBody final LoginVo loginVo) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return ResponseEntity.ok(accessService.login(loginVo.getPlatformName(), loginVo.getAuthCode()));
    }

    @PostMapping("/validations/nickname")
    public ResponseEntity<Void> validateNickname(@RequestBody @Valid final NicknameValidationRequestDto nicknameValidationRequestDto) {
        identityService.validateNickname(nicknameValidationRequestDto);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid final SignUpRequestDto signUpRequestDto) {
        identityService.register(signUpRequestDto);
        return ResponseEntity.ok(null);
    }

}
