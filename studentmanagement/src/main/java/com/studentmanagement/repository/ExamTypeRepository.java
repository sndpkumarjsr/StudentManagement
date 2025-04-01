package com.studentmanagement.repository;

import com.studentmanagement.entity.ExamType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExamTypeRepository extends JpaRepository<ExamType,Integer> {
    Optional<ExamType> findExamTypeByExamName(String examName);
}
