package com.auth.auth.service;

import com.auth.auth.TestConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles({"local"})
@ContextConfiguration(classes = TestConfiguration.class)
public class JwtProviderTest {

    private final JwtProvider jwtProvider = new JwtProvider();

    private final Long userId;

    public JwtProviderTest() throws Exception {
        this.userId = 1L;
    }

    String makeToken() {
        return jwtProvider.issueToken(userId);
    }

    @Test
    @DisplayName("토큰 생성에 성공한다.")
    void should_Return_NotNull_when_Create_Token() {

        //when
        String token = jwtProvider.issueToken(userId);

        //then
        assertThat(token).isNotNull();
    }

    @Test
    @DisplayName("토큰 유효성 검증에 성공한다.")
    void should_ReturnTrue_when_Check_Validation() {

        // given
        String token = makeToken();

        //when
        boolean isValid = jwtProvider.verifyToken(token);

        //then
        assertThat(isValid).isTrue();
    }

    @Test
    @DisplayName("토큰에 담긴 사용자 아이디를 반환한다.")
    void should_ReturnExactUserId_when_getJwtSubject() {

        // given
        String token = makeToken();

        //when
        Long sub = jwtProvider.getJwtSubject(token);

        //then
        assertThat(sub).isEqualTo(userId);
    }
}
