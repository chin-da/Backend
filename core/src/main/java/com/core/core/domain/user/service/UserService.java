package com.core.core.domain.user.service;

import com.core.core.domain.user.User;
import com.core.core.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public boolean isRegisteredUser(final Long socialId) {
        Optional<User> user = userRepository.findBySocialId(socialId);
        return user.isPresent();
    }
}
