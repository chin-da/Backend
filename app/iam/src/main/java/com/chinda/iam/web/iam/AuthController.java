package com.chinda.iam.web.iam;

import com.chinda.iam.application.AccessService;
import com.chinda.iam.domain.access.AccessToken;
import com.chinda.iam.web.iam.dto.request.LoginDTO;
import com.chinda.iam.web.iam.dto.response.PublicKeyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AccessService accessService;

    @PostMapping("/login")
    public ResponseEntity<AccessToken> login(
            @Valid @RequestBody final LoginDTO loginDTO) {
        return ResponseEntity.ok(accessService.tempRegisterAndLogin(loginDTO.getPlatformName(), loginDTO.getAuthCode()));
    }

    @GetMapping("/public-key")
    public ResponseEntity<PublicKeyDTO> getPublicKey() {
        return ResponseEntity.ok(new PublicKeyDTO(accessService.getPublicKey()));
    }
}
