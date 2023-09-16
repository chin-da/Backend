package com.chinda.user.domain.access.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoUserResponse implements OAuthUserResponse {

    @NotNull
    private Long id;

    @Override
    public Long getSocialId() {
        return this.id;
    }
}
