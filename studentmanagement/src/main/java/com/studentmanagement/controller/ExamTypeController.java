package com.studentmanagement.controller;

import com.studentmanagement.dto.ExamTypeDto;
import com.studentmanagement.service.ExamTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/examtypes")
public class ExamTypeController {

    private final ExamTypeService examTypeService;

    public ExamTypeController(ExamTypeService examTypeService) {
        this.examTypeService = examTypeService;
    }

    @GetMapping
    public ResponseEntity<List<ExamTypeDto>> getAll(){
        return ResponseEntity.ok(examTypeService.getAll());
    }

    @PostMapping
    public ResponseEntity<ExamTypeDto> addNewExamType(@RequestBody  ExamTypeDto examTypeDto){
        return ResponseEntity.ok(examTypeService.add(examTypeDto));
    }

    @GetMapping("/{examName}")
    public ResponseEntity<ExamTypeDto> getByName(@PathVariable String examName){
        return ResponseEntity.ok(examTypeService.getByName(examName));
    }

    @PutMapping("/{examName}")
    public ResponseEntity<ExamTypeDto> update(@RequestBody ExamTypeDto dto, @PathVariable String examName){
        return ResponseEntity.ok(examTypeService.update(dto,examName));
    }

    @DeleteMapping("/{examName}")
    public ResponseEntity<?> delete(@PathVariable String examName){
        return (examTypeService.delete(examName))?ResponseEntity.noContent().build():ResponseEntity.internalServerError().build();
    }
}
