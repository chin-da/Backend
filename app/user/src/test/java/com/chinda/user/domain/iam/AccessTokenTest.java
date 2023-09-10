package com.chinda.user.domain.iam;


import org.apache.tomcat.util.codec.binary.Base64;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AccessTokenTest {


    private final Long userId = 1L;
    private final Long defaultExpireTime = 100000L;

    private final String publicKeyPEM = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyRJjbGAYEU2yn1AJwXQxkl+6k1UmuMo80oYU3N33zl1A1Ywuu9MET97YXTWaez/VfJgzuU/uibfopjfyFM/Mzau5QcGEW487RYrSxil+/ZJr1DNEqwR/6ZMn3fpYHjBPjJkVtd/yTWCgrEp+G1PkJxrwOKQb6+/CWj1g3HlM9KQhAEPDggkyfBOt8chojlhrZzvXXRfbv2qCJ4rqRkgJwAcZRt/7CtlYz5CElpNR49iu+CXtcnjTwG5ABBntK9UzsRMXKEnDbwY0R49SKG8sKxay54agT/5kQOk2blXQij2p+PectMcRygcbWIz6c+ucE9Ff+hjY7NH/FaQD2aRwywIDAQAB";
    private final String privateKeyPEM = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDJEmNsYBgRTbKfUAnBdDGSX7qTVSa4yjzShhTc3ffOXUDVjC670wRP3thdNZp7P9V8mDO5T+6Jt+imN/IUz8zNq7lBwYRbjztFitLGKX79kmvUM0SrBH/pkyfd+lgeME+MmRW13/JNYKCsSn4bU+QnGvA4pBvr78JaPWDceUz0pCEAQ8OCCTJ8E63xyGiOWGtnO9ddF9u/aoIniupGSAnABxlG3/sK2VjPkISWk1Hj2K74Je1yeNPAbkAEGe0r1TOxExcoScNvBjRHj1IobywrFrLnhqBP/mRA6TZuVdCKPan495y0xxHKBxtYjPpz65wT0V/6GNjs0f8VpAPZpHDLAgMBAAECggEAATFEua3QNroOkcyjDNdS5pqpZppIrSvgicxrGew5vgA7lDNNS6aIDgPfpfMyxAGwPcRBT5teah6sbd/tuvCIjGe0+jja6hgYB+TiNHP74XvEJpwEj5G/7ciw9ythk7xBnISD5nI6OPnrugSEaZu+bKTyQRkSuVF7W5b67hPMwF6oxo66S+g4AmMRg9mYz7FmOagSgmoxdmIe+QZYIPtG1ZnRexEjclbvxco4VxhBa7kCm+bfF/SU97myESvv8+AfQIrj7J5+Uy0aAf+S/5UelmPNtAR3qBNgR+7njumkP/gXITcmjRlGpD+0AGSUpC4aj+ZqrZGd4q9iMrUr7tYWAQKBgQDLx49RnRJBfi6ukZ1KmOfvFcOuLPQ/4Gtx03Db3/lEzDEM2bEK7lTR1vVvgbT1Wo4uCWC2MuBToxjLdPPFww2O30tFdG4pL55FwaGX8V6z+evLo1Ufb4+XwBIBsaEtdvnAfp31nmMQzUWfCHP3coY4rjJBhlaWGoiWwaZv+4WlgQKBgQD8mTJYcnUFOxlQ4kpMF/1+QeeVxBhF3YOo0pNeUoASGLr9WlU9vPqSyaBEFAl+XKhBGbTAlW9+mElMB3Y1lYUI7/w0Yslqmj1vxlBPMGZsOF/5Y0H3FAGqRMe7qelxRfpL2zVcRl4HAjy1xdWWC3GiL9hC6F4QkHZ+OS5V8r70SwKBgFQMuHFKHtiCihCYoM6+6JL1YCN8CTY7gzWMp3UZGUAmFGkfdOV0TenLBqp6TkAQnKoiVhBwRZRZpgVs9IryG6p9MRexoSo9qMNOzS7VcGE9SONSN+7iXdCSeRrXwAn3k6Ww08gWDvhEIl4o52P9iZzhRHtARIBe6017DAIONR6BAoGAYDnkCfmgRpva6wspjo9dDCoDFPSc4pCeRUPiy/3VuA8fvMAa1OgW9LBGk2imrTKTDinCx9KlpK5SzdnR2FvbUCpMA5DWdfvIB7qegWxIuFVJinGbgYpnj/ufswfpXDNyRE9/pmVQEI9znTN+SCiX41cipWpr2xeNbExQMIHG6L0CgYEAywPhkENeu/Lv+FflhDN1ofTPB4vG/O6xZF87Or1npUH9Lt/t5bTSVFwxo9ZIC30b3JxM5mBxr+Bm+VXZNEqiIgOgl4iAK63pQBzK6+TFHYQk0WlK0jpiX1Dww3M0uCokKC4nGrFkHafRMTTZBLygc3qEiy8abVkOtqLL0s6D5NE=";

    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

    @BeforeEach
    public void setPublicAndPrivateKey() throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        this.publicKey = (RSAPublicKey) keyFactory.generatePublic(new X509EncodedKeySpec(Base64.decodeBase64(publicKeyPEM)));
        this.privateKey =(RSAPrivateKey) keyFactory.generatePrivate(new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyPEM)));
    }

    AccessToken makeNewToken() {
        return new AccessToken(userId, defaultExpireTime, privateKey);
    }

    @Test
    @DisplayName("토큰 생성에 성공한다.")
    void should_Return_NotNull_when_Create_Token() {

        //when
        AccessToken token = new AccessToken(userId, defaultExpireTime, privateKey);

        //then
        assertThat(token).isNotNull();
    }

    @Test
    @DisplayName("기간이 지난 토큰을 검증하면 false를 반환한다.")
    void Should_ReturnFalse_When_TokenExpired() {
        AccessToken token = new AccessToken(userId, 0L, privateKey);

        Assertions.assertThat(token.verifySignature(publicKey)).isFalse();
    }

    @Test
    @DisplayName("토큰 유효성 검증에 성공한다.")
    void should_ReturnTrue_when_Check_Validation() {

        // given
        AccessToken token = makeNewToken();

        //when
        boolean isValid = token.verifySignature(publicKey);

        //then
        assertThat(isValid).isTrue();
    }

    @Test
    @DisplayName("토큰에 담긴 사용자 아이디를 반환한다.")
    void should_ReturnExactUserId_when_getJwtSubject() {

        // given
        AccessToken token = makeNewToken();

        //when
        Long sub = token.getSubject(publicKey);

        //then
        assertThat(sub).isEqualTo(userId);
    }
}
