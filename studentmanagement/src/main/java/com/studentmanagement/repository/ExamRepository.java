package com.studentmanagement.repository;

import com.studentmanagement.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExamRepository extends JpaRepository<Exam,Integer> {
    Optional<Exam> findByNameAndExamTypeExamName(String name, String examName);
}
