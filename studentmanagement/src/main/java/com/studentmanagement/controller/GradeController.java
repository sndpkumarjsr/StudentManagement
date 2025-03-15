package com.studentmanagement.controller;

import com.studentmanagement.entity.Grade;
import com.studentmanagement.repository.GradeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grades")
public class GradeController {

    private final GradeRepository repository;

    public GradeController(GradeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Grade> getAll(){
        return repository.findAll();
    }

    @PostMapping
    public Grade addNewGrade(@RequestBody Grade grade){
        return repository.save(grade);
    }
}
