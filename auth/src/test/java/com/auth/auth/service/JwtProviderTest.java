package com.auth.auth.service;

import com.auth.auth.TestConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles({"local"})
@ContextConfiguration(classes = TestConfiguration.class)
public class JwtProviderTest {

    @Autowired
    private JwtProvider jwtProvider;

    private final Long userId;

    public JwtProviderTest() {
        this.userId = 1L;
    }

    String makeToken() {
        return jwtProvider.issueToken(userId);
    }

    @Test
    @DisplayName("토큰 생성에 성공한다.")
    void should_Success_To_Create_Token() {

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


}
