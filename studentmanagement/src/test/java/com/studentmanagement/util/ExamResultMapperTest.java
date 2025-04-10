//package com.studentmanagement.util;
//
//import com.studentmanagement.dto.ExamResultResponseDto;
//import com.studentmanagement.entity.Exam;
//import com.studentmanagement.entity.ExamResult;
//import com.studentmanagement.entity.ExamType;
//import com.studentmanagement.entity.Student;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDate;
//
//class ExamResultMapperTest {
//
//    private ExamResultMapper examResultMapper;
//
//    @BeforeEach
//    void setUp() {
//        examResultMapper = new ExamResultMapper();
//    }
//
//    @Test
//    public void checkToExamResultResponseDto(){
//        ExamType examType = new ExamType("First Term", 100, 33, "must obtain Pass marks");
//        examType.setId(1);
//        Exam exam = new Exam("Batch 2010-2012", LocalDate.of(2011,10,16));
//        exam.setExamType(examType);
//        exam.setId(1);
//        Student student = new Student("Sandeep","Kumar","sndp@mail.com","abc@123",28,LocalDate.of(1995,12,16),"9988556633",LocalDate.of(2025,03,14));
//        student.setId(1);
//
//        ExamResult examResult = new ExamResult(70.56,exam,student);
//
//        ExamResultResponseDto responseDto = examResultMapper.toExamResultResponseDto(examResult);
//
//        Assertions.assertEquals(responseDto.examTypeName(),examResult.getExam().getExamType().getExamName());
//        Assertions.assertEquals(responseDto.examName(),examResult.getExam().getName());
//        Assertions.assertEquals(responseDto.passMarks(),examResult.getExam().getExamType().getPassMarks());
//        Assertions.assertEquals(responseDto.totalMarks(),examResult.getExam().getExamType().getTotalMarks());
//        Assertions.assertEquals(responseDto.startDate(),examResult.getExam().getStartDate());
//        Assertions.assertEquals(responseDto.StudentName(),examResult.getStudent().getFirstName()+" "+examResult.getStudent().getLastName());
//
//    }
//
//}