package com.studentmanagement.repository;

import com.studentmanagement.entity.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassRoomRepository extends JpaRepository<ClassRoom,Integer> {
    Optional<ClassRoom> findByClassRoomNumber(String classRoomNumber);
}
