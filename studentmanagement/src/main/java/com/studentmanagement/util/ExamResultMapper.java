package com.studentmanagement.util;

import com.studentmanagement.dto.ExamResultDto;
import com.studentmanagement.dto.ExamResultResponseDto;
import com.studentmanagement.entity.Exam;
import com.studentmanagement.entity.ExamResult;
import com.studentmanagement.entity.Student;
import com.studentmanagement.repository.ExamRepository;
import com.studentmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExamResultMapper {


    public ExamResultResponseDto toExamResultResponseDto(ExamResult examResult){
        return new ExamResultResponseDto(
                examResult.getExam().getExamType().getExamName(),
                examResult.getExam().getExamType().getTotalMarks(),
                examResult.getExam().getExamType().getPassMarks(),
                examResult.getExam().getName(),
                examResult.getExam().getStartDate(),
                examResult.getMarks(),
                examResult.getStudent().getFirstName()+ " " +examResult.getStudent().getLastName()
                );
    }

}
