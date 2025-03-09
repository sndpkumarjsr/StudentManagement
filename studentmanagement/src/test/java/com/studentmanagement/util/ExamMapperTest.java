package com.studentmanagement.util;

import com.studentmanagement.dto.ExamDto;
import com.studentmanagement.dto.ExamResponseDto;
import com.studentmanagement.entity.Exam;
import com.studentmanagement.entity.ExamType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ExamMapperTest {

    private ExamMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ExamMapper();
    }

    @Test
    public void checkConvertToExam(){
        ExamDto examDto = new ExamDto("Class X 'B'  Batch:- 2010 - 2012", LocalDate.of(2025,03,15),1);

        Exam exam = mapper.toExam(examDto);

        Assertions.assertEquals(examDto.name(),exam.getName());
        Assertions.assertEquals(examDto.startDate(),exam.getStartDate());
    }

    @Test
    public void checkConvertToExamResponseDto(){
        Exam exam = new Exam("Class X 'B'  Batch:- 2010 - 2012", LocalDate.of(2025,03,15));
        ExamType examType = new ExamType("Mid Term",100,33,"must obtain pass marks");
        exam.setExamType(examType);

        ExamResponseDto saveResponseDto = mapper.toExamResponseDto(exam);

        Assertions.assertEquals(saveResponseDto.name(),exam.getName());
        Assertions.assertEquals(saveResponseDto.startDate(),exam.getStartDate());
        Assertions.assertEquals(saveResponseDto.examName(),exam.getExamType().getExamName());
        Assertions.assertEquals(saveResponseDto.totalMarks(),exam.getExamType().getTotalMarks());
        Assertions.assertEquals(saveResponseDto.passMarks(),exam.getExamType().getPassMarks());
        Assertions.assertEquals(saveResponseDto.description(),exam.getExamType().getDescription());

    }
}