package com.chinda.ranking.domain.record;

import com.chinda.iam_shared_kernel.domain.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends CrudRepository<Record, Long> {

    List<Record> findByUserOrderByCreatedAtDesc(User user);
}
