package com.studentmanagement.controller;

import com.studentmanagement.dto.TeacherDto;
import com.studentmanagement.dto.TeacherResponseDto;
import com.studentmanagement.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TeacherResponseDto>> getAll(){
        var list = service.getAll();
        if(list.isEmpty())
            return ResponseEntity.notFound().build();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TeacherResponseDto> addNewTeacher(@RequestBody TeacherDto teacherDto){
        var saved = service.add(teacherDto);
        if(saved == null)
            return ResponseEntity.internalServerError().build();
        return ResponseEntity.ok(saved);
    }

}
