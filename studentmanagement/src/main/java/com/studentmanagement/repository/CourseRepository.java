package com.studentmanagement.repository;

import com.studentmanagement.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course,Integer> {
    Optional<Course> findByNameAndDescription(String name, String description);
}
