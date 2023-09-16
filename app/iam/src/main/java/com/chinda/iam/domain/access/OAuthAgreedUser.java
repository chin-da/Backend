package com.chinda.iam.domain.access;

import com.chinda.iam.domain.access.dto.OAuthUserResponse;
import com.chinda.iam_shared_kernel.model.Platform;
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
