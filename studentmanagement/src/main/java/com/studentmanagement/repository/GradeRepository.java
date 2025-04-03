package com.studentmanagement.repository;

import com.studentmanagement.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GradeRepository extends JpaRepository<Grade,Integer> {
    Optional<Grade> findByNameAndDescription(String name, String description);
}
