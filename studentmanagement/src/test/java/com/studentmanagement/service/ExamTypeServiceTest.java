package com.studentmanagement.service;

import com.studentmanagement.dto.ExamTypeDto;
import com.studentmanagement.entity.ExamType;
import com.studentmanagement.repository.ExamTypeRepository;
import com.studentmanagement.util.ExamTypeMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.util.List;


class ExamTypeServiceTest {

    @InjectMocks
    private  ExamTypeService examTypeService;

    @Mock
    private ExamTypeMapper examTypeMapper;

    @Mock
    private ExamTypeRepository examTypeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void checkGetAll(){
        ExamType examType = new ExamType("First Term",100,33,"Must Obtain Passing Marks");
        List<ExamType> list = List.of(examType);

        Mockito.when(examTypeRepository.findAll()).thenReturn(list);
        Mockito.when(examTypeMapper.toExamTypeDto(any(ExamType.class))).thenReturn(new ExamTypeDto("First Term",100,33,"Must Obtain Passing Marks"));

        List<ExamTypeDto> dtoList = examTypeService.getAll();

        Assertions.assertEquals(dtoList.size(),list.size());

        Assertions.assertEquals(dtoList.get(0).examName(),list.get(0).getExamName());
        Assertions.assertEquals(dtoList.get(0).totalMarks(),list.get(0).getTotalMarks());
        Assertions.assertEquals(dtoList.get(0).passMarks(),list.get(0).getPassMarks());
        Assertions.assertEquals(dtoList.get(0).description(),list.get(0).getDescription());

        Mockito.verify(examTypeRepository).findAll();
        Mockito.verify(examTypeMapper).toExamTypeDto(any(ExamType.class));

        Mockito.verify(examTypeRepository,times(1)).findAll();
    }

    public void checkAddNewExamType(){

    }
}