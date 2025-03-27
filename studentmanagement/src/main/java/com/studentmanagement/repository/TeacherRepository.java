package com.studentmanagement.repository;

import com.studentmanagement.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
    Optional<Teacher> findByFacultyId(String facultyId);
    Optional<Teacher> findByEmail(String email);
}
