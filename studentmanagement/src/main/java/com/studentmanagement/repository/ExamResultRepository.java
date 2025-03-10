package com.studentmanagement.repository;

import com.studentmanagement.entity.ExamResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamResultRepository extends JpaRepository<ExamResult,Integer> {
}
