package com.studentmanagement.controller;

import com.studentmanagement.dto.ExamDto;
import com.studentmanagement.dto.ExamResponseDto;
import com.studentmanagement.service.ExamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("exams")
public class ExamController {

    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @GetMapping
    public ResponseEntity<List<ExamResponseDto>> getAll(){
        return ResponseEntity.ok(examService.getAll());
    }

    @PostMapping
    public  ResponseEntity<ExamResponseDto> addNewExam(@RequestBody ExamDto examDto){
        return new ResponseEntity<>(examService.add(examDto), HttpStatus.CREATED);
    }

    @GetMapping("/{name}/examType/{examType_examName}")
    public ResponseEntity<ExamResponseDto> getByNameAndExamtypeName(@PathVariable String name,@PathVariable String examType_examName){
        return ResponseEntity.ok(examService.getByNameAndExamtypeName(name,examType_examName));
    }

    @PutMapping("/{name}/examType/{examType_examName}")
    public ResponseEntity<ExamResponseDto> update(@RequestBody ExamDto dto,@PathVariable String name,@PathVariable String examType_examName){
        return ResponseEntity.ok(examService.update(dto,name,examType_examName));
    }

    @DeleteMapping("/{name}/examType/{examType_examName}")
    public ResponseEntity<?> delete(@PathVariable String name,@PathVariable String examType_examName){
        return (examService.delete(name,examType_examName))?ResponseEntity.noContent().build() : ResponseEntity.internalServerError().build();
    }
}
