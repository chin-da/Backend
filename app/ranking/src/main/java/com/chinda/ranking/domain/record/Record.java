package com.chinda.ranking.domain.record;

import com.chinda.common.model.TimestampedEntity;
import com.chinda.ranking.domain.excercise.Exercise;
import com.chinda.iam_shared_kernel.domain.user.User;
import com.chinda.ranking.domain.record.exceptions.CertificateNeededException;
import com.chinda.ranking.domain.record.exceptions.InvalidMeasurementRangeException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
public class Record extends TimestampedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @Column(nullable = false)
    private Float measurement;

    @OneToOne
    @JoinColumn(name = "certificate_id")
    private Certificate certificate;

    private Record (User user, Exercise exercise){
        this.user = user;
        this.exercise = exercise;
    }

    public static Record create(User user, Exercise exercise, Float measurement) {
        return create(user, exercise, measurement, null);
    }

    public static Record create(User user, Exercise exercise, Float measurement, Certificate certificate) {
        Record record = new Record(user, exercise);
        record.updateMeasurement(measurement, certificate);
        return record;
    }

    public void updateMeasurement(Float measurement, Certificate certificate) {
        if (certificate == null && exercise.needsCertificate(measurement)) {
            throw new CertificateNeededException();
        }
        if (!exercise.measurementInValidRange(measurement)) {
            throw new InvalidMeasurementRangeException();
        }
        this.measurement = measurement;
    }
}

