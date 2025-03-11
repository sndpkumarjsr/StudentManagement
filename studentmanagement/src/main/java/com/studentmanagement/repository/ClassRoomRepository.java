package com.studentmanagement.repository;

import com.studentmanagement.entity.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRoomRepository extends JpaRepository<ClassRoom,Integer> {
}
