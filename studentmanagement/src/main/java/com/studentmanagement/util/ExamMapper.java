package com.studentmanagement.util;

import com.studentmanagement.dto.ExamDto;
import com.studentmanagement.dto.ExamResponseDto;
import com.studentmanagement.entity.Exam;
import org.springframework.stereotype.Service;


@Service
public class ExamMapper {

    public Exam toExam(ExamDto examDto){
        return new Exam(examDto.name(),examDto.startDate());
    }

    public ExamResponseDto toExamResponseDto(Exam exam){
        return  new ExamResponseDto(exam.getName(),exam.getStartDate(),exam.getExamType().getExamName(),exam.getExamType().getTotalMarks(),exam.getExamType().getPassMarks(),exam.getExamType().getDescription());
    }

}
