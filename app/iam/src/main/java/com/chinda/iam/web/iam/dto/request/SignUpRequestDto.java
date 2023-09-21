package com.chinda.iam.web.iam.dto.request;

import com.chinda.common.ValidatorMessage;
import com.chinda.iam.domain.access.OAuthAgreedUser;
import com.chinda.iam_shared_kernel.model.Gender;
import com.chinda.iam_shared_kernel.model.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SignUpRequestDto {

    @NotBlank(message = ValidatorMessage.EMPTY_MESSAGE)
    private String nickname;

    @NotBlank(message = ValidatorMessage.EMPTY_MESSAGE)
    private int age;

    @NotBlank(message = ValidatorMessage.EMPTY_MESSAGE)
    private Gender gender;

    @NotBlank(message = ValidatorMessage.EMPTY_MESSAGE)
    private Double height;

    @NotBlank(message = ValidatorMessage.EMPTY_MESSAGE)
    private Double weight;

    @NotBlank(message = ValidatorMessage.EMPTY_MESSAGE)
    private OAuthAgreedUser oAuthAgreedUser;

    private Double squat;

    private Double deadLift;

    private Double benchPress;

    public User toUser() {
        return User.builder()
                .nickname(nickname)
                .age(age)
                .gender(gender)
                .height(height)
                .weight(weight)
                .socialId(oAuthAgreedUser.getSocialId())
                .platform(oAuthAgreedUser.getPlatform())
                .build();
    }


}
