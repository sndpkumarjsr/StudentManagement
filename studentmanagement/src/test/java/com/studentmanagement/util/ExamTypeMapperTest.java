package com.studentmanagement.util;

import com.studentmanagement.dto.ExamTypeDto;
import com.studentmanagement.entity.ExamType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExamTypeMapperTest {

    private ExamTypeMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ExamTypeMapper();
    }

    @AfterEach
    void tearDown() {
        mapper = null;
    }

    @Test
    public void checkConvertDtoToExamType(){
        ExamTypeDto dto = new ExamTypeDto("Mid Term",100,36,"Mid Term is Importent for Total Grace");

        ExamType examType = mapper.toExamType(dto);

        Assertions.assertEquals(dto.examName(),examType.getExamName());
        Assertions.assertEquals(dto.totalMarks(),examType.getTotalMarks());
        Assertions.assertEquals(dto.passMarks(),examType.getPassMarks());
        Assertions.assertEquals(dto.description(),examType.getDescription());

    }

    @Test
    public void checkConvertExamTypeToDto(){
        ExamType examType = new ExamType("Mid Term",100,36,"Mid Term is Importent for Total Grace");

        ExamTypeDto dto = mapper.toExamTypeDto(examType);

        Assertions.assertEquals(dto.examName(),examType.getExamName());
        Assertions.assertEquals(dto.totalMarks(),examType.getTotalMarks());
        Assertions.assertEquals(dto.passMarks(),examType.getPassMarks());
        Assertions.assertEquals(dto.description(),examType.getDescription());

    }
}
