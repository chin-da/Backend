package com.chinda.iam.web.iam.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.chinda.common.ValidatorMessage.EMPTY_MESSAGE;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NicknameValidationRequestDto {

    @NotBlank(message = EMPTY_MESSAGE)
    private String nickname;

}
