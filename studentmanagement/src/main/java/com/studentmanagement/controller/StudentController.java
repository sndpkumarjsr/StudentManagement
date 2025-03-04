package com.studentmanagement.controller;

import com.studentmanagement.dto.StudentDto;
import com.studentmanagement.dto.StudentResponseDto;
import com.studentmanagement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentResponseDto> getAllStudent(){
        return studentService.getAll();
    }

    @PostMapping
    public StudentResponseDto addStudent(@Valid @RequestBody StudentDto studentDto){
        return studentService.addStudent(studentDto);
    }

}
