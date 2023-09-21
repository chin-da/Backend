package com.chinda.iam.application;

import com.chinda.common.exception.MessageConstants;
import com.chinda.iam.application.exceptions.DuplicateNicknameException;
import com.chinda.iam.domain.identity.UserRepository;
import com.chinda.iam.web.iam.dto.request.NicknameValidationRequestDto;
import com.chinda.iam.web.iam.dto.request.SignUpRequestDto;
import com.chinda.iam_shared_kernel.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IdentityService {

    private final UserRepository userRepository;

    public void register(final SignUpRequestDto signUpRequestDto) {
        User user = signUpRequestDto.toUser();
        userRepository.save(user);
        // TODO: Record 저장 기능 구현 이후 추가
    }

    public void validateNickname(final NicknameValidationRequestDto nicknameValidationRequestDto) {
        String nickname = nicknameValidationRequestDto.getNickname();

        if (userRepository.existsByNickname(nickname)) {
            throw new DuplicateNicknameException(MessageConstants.CONFLICT_NICKNAME.getMessage());
        }
    }

}
