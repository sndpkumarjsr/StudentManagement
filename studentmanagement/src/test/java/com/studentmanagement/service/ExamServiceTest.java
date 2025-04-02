package com.studentmanagement.service;

import com.studentmanagement.dto.ExamDto;
import com.studentmanagement.dto.ExamResponseDto;
import com.studentmanagement.entity.Exam;
import com.studentmanagement.entity.ExamType;
import com.studentmanagement.repository.ExamRepository;
import com.studentmanagement.repository.ExamTypeRepository;
import com.studentmanagement.util.ExamMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class ExamServiceTest {

    @InjectMocks
    private ExamService examService;

    @Mock
    private ExamRepository examRepository;

    @Mock
    private ExamMapper examMapper;

    @Mock
    private ExamTypeRepository examTypeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void checkGetAll(){
        Exam exam = new Exam("Class X 'B'  Batch:- 2010 - 2012", LocalDate.of(2025,03,15));
        ExamType examType = new ExamType("Mid Term",100,33,"must obtain pass marks");
        exam.setExamType(examType);
        List<Exam> list = List.of(exam);

        Mockito.when(examRepository.findAll()).thenReturn(list);
        Mockito.when(examMapper.toExamResponseDto(any(Exam.class)))
                .thenReturn(new ExamResponseDto("Class X 'B'  Batch:- 2010 - 2012", LocalDate.of(2025,03,15),"Mid Term",100,33,"must obtain pass marks"));

        List<ExamResponseDto> returnResponseDto = examService.getAll();

        Assertions.assertEquals(returnResponseDto.get(0).name(),exam.getName());
        Assertions.assertEquals(returnResponseDto.get(0).startDate(),exam.getStartDate());
        Assertions.assertEquals(returnResponseDto.get(0).examName(),exam.getExamType().getExamName());
        Assertions.assertEquals(returnResponseDto.get(0).totalMarks(),exam.getExamType().getTotalMarks());
        Assertions.assertEquals(returnResponseDto.get(0).passMarks(),exam.getExamType().getPassMarks());
        Assertions.assertEquals(returnResponseDto.get(0).description(),exam.getExamType().getDescription());

        Mockito.verify(examRepository,Mockito.times(1)).findAll();
    }

    @Test
    public void checkAdd(){
        ExamDto dto = new ExamDto("Class X 'B'  Batch:- 2010 - 2012", LocalDate.of(2025,03,15),1);

        Exam exam = new Exam("Class X 'B'  Batch:- 2010 - 2012", LocalDate.of(2025,03,15));
        ExamType examType = new ExamType("Mid Term",100,33,"must obtain pass marks");
        examType.setId(1);
        exam.setExamType(examType);

        Exam saveExam = new Exam("Class X 'B'  Batch:- 2010 - 2012", LocalDate.of(2025,03,15));
        saveExam.setExamType(examType);
        saveExam.setId(1);

        Mockito.when(examMapper.toExam(dto)).thenReturn(exam);
        Mockito.when(examTypeRepository.findById(dto.examType_examName())).thenReturn(Optional.of(examType));
        Mockito.when(examRepository.save(exam)).thenReturn(saveExam);
        Mockito.when(examMapper.toExamResponseDto(saveExam))
                .thenReturn(new ExamResponseDto("Class X 'B'  Batch:- 2010 - 2012", LocalDate.of(2025,03,15),"Mid Term",100,33,"must obtain pass marks"));

        ExamResponseDto saveResponseDto = examService.add(dto);

        Assertions.assertEquals(dto.name(),saveResponseDto.name());
        Assertions.assertEquals(dto.startDate(),saveResponseDto.startDate());

        Mockito.verify(examMapper,Mockito.times(1)).toExam(dto);
        Mockito.verify(examTypeRepository,Mockito.times(1)).findById(dto.examType_examName());
        Mockito.verify(examRepository,Mockito.times(1)).save(exam);
        Mockito.verify(examMapper,Mockito.times(1)).toExamResponseDto(saveExam);
    }
}