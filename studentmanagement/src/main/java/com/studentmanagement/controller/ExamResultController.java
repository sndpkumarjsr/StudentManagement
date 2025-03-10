package com.studentmanagement.controller;

import com.studentmanagement.dto.ExamResultDto;
import com.studentmanagement.dto.ExamResultResponseDto;
import com.studentmanagement.entity.ExamResult;
import com.studentmanagement.repository.ExamResultRepository;
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
        var list = examResultService.getAll();
        if(list.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<ExamResultResponseDto> addNewExamResult(@RequestBody ExamResultDto examResultDto){
        var examResultResponse = examResultService.add(examResultDto);
        if(examResultResponse == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(examResultResponse);
    }
}
