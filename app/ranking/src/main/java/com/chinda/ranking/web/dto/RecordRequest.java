package com.chinda.ranking.web.dto;

import lombok.Data;

@Data
public class RecordRequest {
    Long userId;
    String exerciseCode;
    Float measurement;
    Long CertificateId;
}
