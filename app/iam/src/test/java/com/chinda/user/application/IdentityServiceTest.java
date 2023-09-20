package com.chinda.user.application;

import com.chinda.iam.application.IdentityService;
import com.chinda.iam.application.exceptions.DuplicateNicknameException;
import com.chinda.iam.web.iam.dto.request.NicknameValidationRequestDto;
import com.chinda.iam_shared_kernel.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class IdentityServiceTest {

    @Autowired
    private IdentityService identityService;

    public IdentityServiceTest(IdentityService identityService) {
        this.identityService = identityService;
    }

    @Test
    @DisplayName("이미 존재하는 닉네임을 중복확인할 경우 예외를 던진다.")
    void should_ThrowException_when_NicknameIsAlreadyExist() {

        //given
        String duplicateNickname = "test_nickname";

        //when
        User user = User.builder()
                .nickname(duplicateNickname).build();

        //then
        Assertions.assertThrows(DuplicateNicknameException.class, () -> {
            identityService.validateNickname(new NicknameValidationRequestDto(duplicateNickname));
        });
    }
}
