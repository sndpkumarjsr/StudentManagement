package com.studentmanagement.repository;

import com.studentmanagement.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance,Integer> {
    List<Attendance> findByStudentId(Integer studentId);
    Optional<Attendance> findByDateAndStudentId(LocalDate date,Integer studentId);
}
