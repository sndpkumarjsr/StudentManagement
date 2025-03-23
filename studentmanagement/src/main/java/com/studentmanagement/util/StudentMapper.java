package com.studentmanagement.util;

import com.studentmanagement.dto.StudentDto;
import com.studentmanagement.dto.StudentResponseDto;
import com.studentmanagement.entity.School;
import com.studentmanagement.entity.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {
    public StudentResponseDto toStudentResponseDto(Student student){
        return new StudentResponseDto(student.getAdmissionNumber(),student.getFirstName(),student.getLastName(),student.getEmail(),student.getAge(),student.getDateOfBirth(),student.getPhone(),student.getGuardian());
    }

    public Student toStudent(StudentDto studentDto){
        Student student = new Student();
        student.setFirstName(studentDto.firstName());
        student.setLastName(studentDto.lastName());
        student.setEmail(studentDto.email());
        student.setPassword(studentDto.password());
        student.setAge(studentDto.age());
        student.setPhone(studentDto.phone());
        student.setDateOfBirth(studentDto.dateOfBirth());
        student.setDateOfjoining(studentDto.dateOfjoining());

        School school = new School();
        school.setId(studentDto.schoolId());

        student.setSchool(school);

        return  student;
    }
}
