package com.studentmanagement.controller;

import com.studentmanagement.dto.StudentDto;
import com.studentmanagement.dto.StudentResponseDto;
import com.studentmanagement.entity.Student;
import com.studentmanagement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<StudentResponseDto>> getAllStudent(){
        return ResponseEntity.ok(studentService.getAll());
    }

    @PostMapping
    public ResponseEntity<StudentResponseDto> addStudent(@Valid @RequestBody StudentDto studentDto){
        return new ResponseEntity<>(studentService.addStudent(studentDto), HttpStatus.CREATED);
    }

    @GetMapping("/{admissionNumber}")
    public ResponseEntity<StudentResponseDto> findById(@PathVariable String admissionNumber){
        return ResponseEntity.ok(studentService.findById(admissionNumber));
    }

    @PutMapping("/update")
    public ResponseEntity<StudentResponseDto> update(@RequestParam String admissionNumber,@RequestBody StudentDto dto){
        return ResponseEntity.ok(studentService.update(admissionNumber,dto));
    }

    @PutMapping("/schools")
    public ResponseEntity<StudentResponseDto> mapSchool(@RequestParam Integer studentId, @RequestParam Integer schoolId){
        return ResponseEntity.ok(studentService.mapSchool(studentId,schoolId));
    }

    @PutMapping("/guardians")
    public ResponseEntity<StudentResponseDto> mapGuardian(@RequestParam Integer studentId,@RequestParam Integer guardianId){
        return ResponseEntity.ok(studentService.mapGuardian(studentId,guardianId));
    }

    @PutMapping("/classrooms")
    public ResponseEntity<StudentResponseDto> mapClassRooom(@RequestParam Integer studentId,@RequestParam Integer classroomId){
        return ResponseEntity.ok(studentService.mapClassRoom(studentId,classroomId));
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam String admissionNumber){
        return (studentService.delete(admissionNumber))?new ResponseEntity<>(admissionNumber + " is Deleted",HttpStatus.NO_CONTENT):ResponseEntity.internalServerError().build();
    }

}
