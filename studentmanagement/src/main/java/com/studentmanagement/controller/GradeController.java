package com.studentmanagement.controller;

import com.studentmanagement.dto.GradeDto;
import com.studentmanagement.service.GradeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grades")
public class    GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping
    public ResponseEntity<List<GradeDto>> getAll(){
        return ResponseEntity.ok(gradeService.getAll());
    }

    @PostMapping
    public ResponseEntity<GradeDto> addNewGrade(@RequestBody GradeDto gradeDto){
        return ResponseEntity.ok(gradeService.add(gradeDto));
    }

    @GetMapping("/{name}/{description}")
    public ResponseEntity<GradeDto> getByNameAndDescription(@PathVariable String name,@PathVariable String description){
        return ResponseEntity.ok(gradeService.getByGrade(name,description));
    }

    @PutMapping("/{name}/{description}")
    public ResponseEntity<GradeDto> update(@RequestBody GradeDto dto,@PathVariable String name,@PathVariable String description){
        return ResponseEntity.ok(gradeService.update(dto,name,description));
    }

    @DeleteMapping("/{name}/{description}")
    public ResponseEntity<?> delete(@PathVariable String name,@PathVariable String description){
        return (gradeService.delete(name,description))?ResponseEntity.noContent().build() : ResponseEntity.internalServerError().build();
    }
}
