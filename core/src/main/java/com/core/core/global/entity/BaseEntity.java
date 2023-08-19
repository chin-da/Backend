package com.core.core.global.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String TIME_ZONE = "Asia/Seoul";

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT,
            timezone = TIME_ZONE)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT,
            timezone = TIME_ZONE)
    private LocalDateTime updatedAt;
}
