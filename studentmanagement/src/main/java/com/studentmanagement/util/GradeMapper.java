package com.studentmanagement.util;

import com.studentmanagement.dto.GradeDto;
import com.studentmanagement.entity.Grade;
import org.springframework.stereotype.Service;

@Service
public class GradeMapper {

    public Grade toGrade(GradeDto dto){
        return new Grade(dto.name(),dto.description());
    }

    public GradeDto toGradeDto(Grade grade){
        return  new GradeDto(grade.getName(), grade.getDescription());
    }

}
