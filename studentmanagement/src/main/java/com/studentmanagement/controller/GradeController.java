package com.studentmanagement.controller;

import com.studentmanagement.dto.GradeDto;
import com.studentmanagement.service.GradeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grades")
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping
    public ResponseEntity<List<GradeDto>> getAll(){
        var list = gradeService.getAll();
        if(list.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<GradeDto> addNewGrade(@RequestBody GradeDto gradeDto){
        var grade = gradeService.add(gradeDto);
        if(grade ==  null)
            return ResponseEntity.internalServerError().build();
        return ResponseEntity.ok(grade);
    }
}
