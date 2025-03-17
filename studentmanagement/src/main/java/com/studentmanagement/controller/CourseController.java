package com.studentmanagement.controller;

import com.studentmanagement.dto.CourseDto;
import com.studentmanagement.dto.CourseResponseDto;
import com.studentmanagement.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CourseResponseDto>> getAll(){
        var list = service.getAll();
        if(list.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<CourseDto> addNewCourse(@RequestBody CourseDto courseDto){
        var savedResponse = service.add(courseDto);
        if(savedResponse == null)
            return ResponseEntity.internalServerError().build();
        return new ResponseEntity<>(savedResponse, HttpStatus.CREATED);
    }

    @PutMapping("/grades")
    public ResponseEntity<CourseResponseDto> mapToGrade(@RequestParam Integer courseId, @RequestParam Integer gradeId){
        var response = service.mapToGrade(courseId,gradeId);
        if(response == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(response);
    }
}
