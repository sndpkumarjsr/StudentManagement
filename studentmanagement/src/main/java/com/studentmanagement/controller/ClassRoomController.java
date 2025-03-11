package com.studentmanagement.controller;

import com.studentmanagement.entity.ClassRoom;
import com.studentmanagement.repository.ClassRoomRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classrooms")
public class ClassRoomController {

    private final ClassRoomRepository classRoomRepository;

    public ClassRoomController(ClassRoomRepository classRoomRepository) {
        this.classRoomRepository = classRoomRepository;
    }

    @GetMapping
    public List<ClassRoom> getAll(){
        return classRoomRepository.findAll();
    }

    @PostMapping
    public ClassRoom addNewClassRoom(@RequestBody ClassRoom classRoom){
        return classRoomRepository.save(classRoom);
    }
}
