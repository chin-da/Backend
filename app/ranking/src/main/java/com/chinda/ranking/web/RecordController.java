package com.chinda.ranking.web;

import com.chinda.ranking.application.RecordService;
import com.chinda.ranking.domain.excercise.Exercise;
import com.chinda.ranking.domain.record.Certificate;
import com.chinda.ranking.domain.record.Record;
import com.chinda.ranking.web.dto.RecordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ranking/records")
public class RecordController {
    private final RecordService recordService;

    @GetMapping("")
    public ResponseEntity<List<Record>> getUserRecords(@RequestParam Long userId) {
        List<Record> records = recordService.getUserRecords(userId);
        return ResponseEntity.ok(records);
    }


    @PostMapping("")
    public ResponseEntity<Record> createUserRecord(
            @RequestBody RecordRequest recordRequest
    ) {
//        TODO: validate a user token and delete userId in recordRequest
        Record record = recordService.createRecord(recordRequest);
        return ResponseEntity.ok(record);
    }


}
