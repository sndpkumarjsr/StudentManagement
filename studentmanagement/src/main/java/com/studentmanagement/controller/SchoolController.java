package com.studentmanagement.controller;


import com.studentmanagement.dto.SchoolDto;

import com.studentmanagement.service.SchoolService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schools")
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping
    public List<SchoolDto> getAllSchool(){
        return schoolService.getAll();
    }

    @PostMapping
    public SchoolDto addSchool(@Valid @RequestBody SchoolDto schoolDto){
        return schoolService.addSchool(schoolDto);
    }

}
