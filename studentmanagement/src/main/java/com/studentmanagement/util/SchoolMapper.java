package com.studentmanagement.util;

import com.studentmanagement.dto.SchoolDto;
import com.studentmanagement.entity.School;
import org.springframework.stereotype.Service;

@Service
public class SchoolMapper {

    public SchoolDto toSchoolDto(School school){
        return new SchoolDto(school.getName());
    }

    public School toSchool(SchoolDto schoolDto){ return new School(schoolDto.name());}

}
