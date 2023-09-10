package com.api.api.auth.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginVo {

    @NotBlank(message = "플랫폼명은 필수 값입니다.")
    private String platformName;
    @NotBlank(message = "사용자 코드는 필수 값입니다.")
    private String authCode;

}
