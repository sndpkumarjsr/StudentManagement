package com.studentmanagement.repository;

import com.studentmanagement.entity.ExamResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExamResultRepository extends JpaRepository<ExamResult,Integer> {
    Optional<ExamResult> findByExam_NameAndStudent_AdmissionNumberAndCourse_Name(
            String examName,
            String admissionNumber,
            String courseName
    );
}
