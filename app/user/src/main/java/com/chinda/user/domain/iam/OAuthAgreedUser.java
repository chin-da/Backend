package com.chinda.user.domain.iam;

import com.chinda.user_shared_kernel.model.Platform;
import com.chinda.user.domain.iam.dto.OAuthUserResponse;
import lombok.Getter;

@Getter
public class OAuthAgreedUser {
    private Platform platform;
    private Long socialId;

    OAuthAgreedUser(Platform platform, OAuthUserResponse response) {
        this.platform = platform;
        this.socialId = response.getSocialId();
    }


}
