package com.chinda.iam.web.iam.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginVo {

    @NotBlank(message = "플랫폼명은 필수 값입니다.")
    private String platformName;
    @NotBlank(message = "사용자 코드는 필수 값입니다.")
    private String authCode;

}
