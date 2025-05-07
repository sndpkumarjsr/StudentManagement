package com.studentmanagement.controller;

import com.studentmanagement.dto.ExamResultDto;
import com.studentmanagement.dto.ExamResultResponseDto;
import com.studentmanagement.service.ExamResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam_results")
public class ExamResultController {

  private final ExamResultService examResultService;

    public ExamResultController(ExamResultService examResultService) {
        this.examResultService = examResultService;
    }

    @GetMapping
    public ResponseEntity<List<ExamResultResponseDto>> getAll(){
        return ResponseEntity.ok(examResultService.getAll());
    }

    @PostMapping
    public ResponseEntity<ExamResultResponseDto> addNewExamResult(@RequestBody ExamResultDto examResultDto){
        return ResponseEntity.ok(examResultService.add(examResultDto));
    }

    @PutMapping
    public ResponseEntity<ExamResultResponseDto> updateMarks(@RequestBody ExamResultDto dto){
        return ResponseEntity.ok(examResultService.updateMarks(dto));
    }

}
