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
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<CourseDto> addNewCourse(@RequestBody CourseDto courseDto){
        return new ResponseEntity<>(service.add(courseDto), HttpStatus.CREATED);
    }

    @PutMapping("/{courseName}/{courseDescription}/grades/{gradeName}/{gradeDescription}")
    public ResponseEntity<CourseResponseDto> mapToGrade(@PathVariable String courseName, @PathVariable String courseDescription,@PathVariable String gradeName,@PathVariable String gradeDescription){
        return ResponseEntity.ok(service.mapToGrade(courseName, courseDescription, gradeName, gradeDescription));
    }

    @GetMapping("/{courseName}/{courseDescription}")
    public ResponseEntity<CourseResponseDto> getByNameAndDescription(@PathVariable String courseName, @PathVariable String courseDescription){
        return ResponseEntity.ok(service.getByNameAndDescription(courseName,courseDescription));
    }

}
