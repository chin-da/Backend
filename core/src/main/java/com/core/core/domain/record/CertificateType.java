package com.core.core.domain.record;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum CertificateType {
    VIDEO("video"),
    PHOTO("photo");

    private final String typeName;
}
