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
        var list = examTypeService.getAll();
        if(list.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<ExamTypeDto> addNewExamType(@RequestBody  ExamTypeDto examTypeDto){
        ExamTypeDto saveExamTypeDto = examTypeService.add(examTypeDto);
        if(saveExamTypeDto == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(saveExamTypeDto);
    }

    @GetMapping("/byexamname/{examName}")
    public ResponseEntity<ExamTypeDto> getByName(@PathVariable String examName){
        ExamTypeDto examTypeDto = examTypeService.getByName(examName);
        if(examTypeDto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(examTypeDto);
    }
}
