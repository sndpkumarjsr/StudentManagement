package com.studentmanagement.util;

import com.studentmanagement.dto.TeacherDto;
import com.studentmanagement.dto.TeacherResponseDto;
import com.studentmanagement.entity.Teacher;
import org.springframework.stereotype.Service;

@Service
public class TeacherMapper {

    public Teacher toTeacher(TeacherDto dto){
        return new Teacher(dto.firstName(),dto.lastName(),dto.email(),dto.password(),dto.dateOfBirth(),dto.phone());
    }

    public TeacherResponseDto toTeacherResponseDto(Teacher teacher){
        return new TeacherResponseDto(teacher.getFirstName(),teacher.getLastName(),teacher.getEmail(),teacher.getPassword(),teacher.getDateOfBirth(),teacher.getPhone(),teacher.getCourse().getName(),teacher.getCourse().getDescription());
    }

}
