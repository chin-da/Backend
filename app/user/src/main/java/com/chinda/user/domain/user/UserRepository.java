package com.chinda.user.domain.user;

import com.chinda.user_shared_kernel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserBySocialId(final Long socialId);
}