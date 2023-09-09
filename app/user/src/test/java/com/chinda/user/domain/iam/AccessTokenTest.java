package com.chinda.user.domain.iam;



import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles({"local"})
public class AccessTokenTest {


    private final Long userId = 1L;
    private final Long defaultExpireTime = 1000L;
    private final String publicKey = "temp";
    private final String privateKey = "temp";

    AccessToken makeToken() {
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
    @DisplayName("토큰 유효성 검증에 성공한다.")
    void should_ReturnTrue_when_Check_Validation() {

        // given
        AccessToken token = makeToken();

        //when
        boolean isValid = token.verifySignature(publicKey);

        //then
        assertThat(isValid).isTrue();
    }

    @Test
    @DisplayName("토큰에 담긴 사용자 아이디를 반환한다.")
    void should_ReturnExactUserId_when_getJwtSubject() {

        // given
        AccessToken token = makeToken();

        //when
        Long sub = token.getSubject(publicKey);

        //then
        assertThat(sub).isEqualTo(userId);
    }
}
