package com.studentmanagement.controller;

import com.studentmanagement.dto.TeacherDto;
import com.studentmanagement.dto.TeacherResponseDto;
import com.studentmanagement.service.TeacherService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private static final Logger log = LoggerFactory.getLogger(TeacherController.class);
    private final TeacherService service;

    @Autowired
    public TeacherController(TeacherService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TeacherResponseDto>> getAll() {
        log.info("Fetching all teachers");
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<TeacherResponseDto> addNewTeacher(@RequestBody TeacherDto teacherDto) {
        log.info("Adding new teacher with email: {}", teacherDto.email());
        return ResponseEntity.ok(service.add(teacherDto));
    }

    @GetMapping("/{facultyId}")
    public ResponseEntity<TeacherResponseDto> getByFacultyId(@PathVariable String facultyId) {
        log.info("Finding teacher by faculty ID: {}", facultyId);
        return ResponseEntity.ok(service.getByFacultyId(facultyId));
    }

    @PutMapping("/{facultyId}")
    public ResponseEntity<TeacherResponseDto> update(@RequestBody TeacherDto dto, @PathVariable String facultyId) {
        log.info("Updating teacher details for faculty ID: {}", facultyId);
        return ResponseEntity.ok(service.update(dto, facultyId));
    }

    @DeleteMapping("/{facultyId}")
    public ResponseEntity<Void> delete(@PathVariable String facultyId) {
        log.info("Deleting teacher with faculty ID: {}", facultyId);
        return (service.delete(facultyId)) ? ResponseEntity.noContent().build() : ResponseEntity.internalServerError().build();
    }
}
