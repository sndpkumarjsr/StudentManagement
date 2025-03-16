package com.studentmanagement.service;

import com.studentmanagement.dto.GradeDto;
import com.studentmanagement.entity.Grade;
import com.studentmanagement.repository.GradeRepository;
import com.studentmanagement.util.GradeMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;
    private final GradeMapper gradeMapper;

    public GradeService(GradeRepository gradeRepository, GradeMapper gradeMapper) {
        this.gradeRepository = gradeRepository;
        this.gradeMapper = gradeMapper;
    }

    public List<GradeDto> getAll(){
        return gradeRepository.findAll()
                .stream().map(gradeMapper::toGradeDto)
                .collect(Collectors.toList());
    }

    public GradeDto add(GradeDto dto){
        Grade grade = gradeMapper.toGrade(dto);
        grade.setCreatedBy("Admin");
        grade.setCreatedAt(LocalDateTime.now());
        Grade savedGrade = gradeRepository.save(grade);
        return gradeMapper.toGradeDto(savedGrade);
    }

}
