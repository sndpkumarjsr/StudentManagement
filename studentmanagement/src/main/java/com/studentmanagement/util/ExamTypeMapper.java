package com.studentmanagement.util;

import com.studentmanagement.dto.ExamTypeDto;
import com.studentmanagement.entity.ExamType;
import org.springframework.stereotype.Service;

@Service
public class ExamTypeMapper {

    public ExamType toExamType(ExamTypeDto examTypeDto){
        return new ExamType(examTypeDto.examName(),examTypeDto.totalMarks(), examTypeDto.passMarks(), examTypeDto.description());
    }

    public ExamTypeDto toExamTypeDto(ExamType examType){
        return new ExamTypeDto(examType.getExamName(), examType.getTotalMarks(), examType.getPassMarks(), examType.getDescription());
    }

}
