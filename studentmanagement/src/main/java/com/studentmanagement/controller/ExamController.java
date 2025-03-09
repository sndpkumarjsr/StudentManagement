package com.studentmanagement.controller;

import com.studentmanagement.dto.ExamDto;
import com.studentmanagement.dto.ExamResponseDto;
import com.studentmanagement.entity.Exam;
import com.studentmanagement.repository.ExamRepository;
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
        var list = examService.getAll();
        if(list.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public  ResponseEntity<ExamResponseDto> addNewExam(@RequestBody ExamDto examDto){
        ExamResponseDto saveResponseDto = examService.add(examDto);
        if(saveResponseDto == null)
            return ResponseEntity.badRequest().build();
        return new ResponseEntity<>(saveResponseDto, HttpStatus.CREATED);
    }
}
