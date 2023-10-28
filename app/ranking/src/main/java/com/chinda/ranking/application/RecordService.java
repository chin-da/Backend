package com.chinda.ranking.application;

import com.chinda.iam_shared_kernel.domain.user.User;
import com.chinda.ranking.domain.record.UserRepository;
import com.chinda.ranking.domain.excercise.Exercise;
import com.chinda.ranking.domain.excercise.ExerciseRepository;
import com.chinda.ranking.domain.record.Record;
import com.chinda.ranking.domain.record.RecordRepository;
import com.chinda.ranking.web.dto.RecordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;
    public Record createRecord(RecordRequest recordRequest) {
        User user = userRepository.findById(recordRequest.getUserId()).orElseThrow();
        Exercise exercise = exerciseRepository.findById(recordRequest.getExerciseCode()).orElseThrow();
//        TODO: add certificate upload
        Record record = Record.create(user, exercise, recordRequest.getMeasurement(), null);
        recordRepository.save(record);
        return record;
    }

    public List<Record> getUserRecords(Long userId) {
        return recordRepository.findByUserOrderByCreatedAtDesc(User.builder().id(userId).build());
    }

    public void deleteRecord(Long id) {
        recordRepository.deleteById(id);
    }
}
