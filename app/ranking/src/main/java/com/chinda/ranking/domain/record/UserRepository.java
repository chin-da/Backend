package com.chinda.ranking.domain.record;

import com.chinda.iam_shared_kernel.domain.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
