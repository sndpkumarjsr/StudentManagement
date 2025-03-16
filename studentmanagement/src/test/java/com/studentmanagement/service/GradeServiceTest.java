package com.studentmanagement.service;

import com.studentmanagement.dto.GradeDto;
import com.studentmanagement.entity.Grade;
import com.studentmanagement.repository.GradeRepository;
import com.studentmanagement.util.GradeMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GradeServiceTest {

    @InjectMocks
    private GradeService gradeService;
    @Mock
    private GradeRepository gradeRepository;
    @Mock
    private GradeMapper gradeMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void checkAdd(){
        GradeDto dto = new GradeDto("X","Batch 2025-2026");
        Grade grade = new Grade("X","Batch 2025-2026");
        Grade savedGrade = new Grade("X","Batch 2025-2026");
        savedGrade.setId(1);
        GradeDto responseDto = new GradeDto("X","Batch 2025-2026");

        Mockito.when(gradeMapper.toGrade(dto)).thenReturn(grade);
        Mockito.when(gradeRepository.save(grade)).thenReturn(savedGrade);
        Mockito.when(gradeMapper.toGradeDto(savedGrade)).thenReturn(responseDto);

        GradeDto savedDto = gradeService.add(dto);

        Assertions.assertEquals(savedDto.name(),dto.name());
        Assertions.assertEquals(savedDto.description(),dto.description());

        Mockito.verify(gradeRepository,Mockito.times(1)).save(grade);
    }

    @Test
    public void checkGetAll(){
        Grade grade = new Grade("X","Batch 2025-2026");
        grade.setId(1);

        List<Grade> list = List.of(grade);

        Mockito.when(gradeRepository.findAll()).thenReturn(list);
        Mockito.when(gradeMapper.toGradeDto(Mockito.any(Grade.class))).thenReturn(new GradeDto("X","Batch 2025-2026"));

        List<GradeDto> dto = gradeService.getAll();

        Assertions.assertEquals(dto.get(0).name(),grade.getName());
        Assertions.assertEquals(dto.get(0).description(),grade.getDescription());

        Mockito.verify(gradeRepository,Mockito.times(1)).findAll();
    }
}