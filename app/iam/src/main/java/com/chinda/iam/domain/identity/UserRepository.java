package com.chinda.iam.domain.identity;

import com.chinda.iam_shared_kernel.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserBySocialId(final Long socialId);
}