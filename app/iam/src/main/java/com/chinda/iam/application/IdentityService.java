package com.chinda.iam.application;

import com.chinda.common.exception.MessageConstants;
import com.chinda.iam.application.exceptions.DuplicateNicknameException;
import com.chinda.iam.domain.identity.UserRepository;
import com.chinda.iam.web.iam.dto.request.NicknameValidationRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IdentityService {

    private final UserRepository userRepository;

    public void validateNickname(final NicknameValidationRequestDto nicknameValidationRequestDto) {
        String nickname = nicknameValidationRequestDto.getNickname();

        if (userRepository.existsByNickname(nickname)) {
            throw new DuplicateNicknameException(MessageConstants.CONFLICT_NICKNAME.getMessage());
        }
    }

}
