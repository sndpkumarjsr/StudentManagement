package com.studentmanagement.util;

import com.studentmanagement.dto.StudentDto;
import com.studentmanagement.dto.StudentResponseDto;
import com.studentmanagement.entity.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class StudentMapperTest {

    private StudentMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new StudentMapper();
    }

    @Test
    public void checkConvertStudentToStudentResponseDto(){
        Student student = new Student("Sandeep","Kumar","sndp@mail.com",15, LocalDate.of(1995, 12, 16),"9988776655", LocalDate.of(2025,03,20));

        StudentResponseDto responseDto = mapper.toStudentResponseDto(student);

        Assertions.assertEquals(student.getFirstName(),responseDto.firstName());
        Assertions.assertEquals(student.getLastName(),responseDto.lastName());
        Assertions.assertEquals(student.getEmail(),responseDto.email());
    }

    @Test
    public void checkConvertStudentDtoToStudent(){
        StudentDto studentDto = new StudentDto("Sandeep","Kumar","sndp@mail.com",28, LocalDate.of(1995, 12, 16),"9988776655", LocalDate.of(2025,03,20),1);

        Student student = mapper.toStudent(studentDto);

        Assertions.assertEquals(studentDto.firstName(),student.getFirstName());
        Assertions.assertEquals(studentDto.lastName(),student.getLastName());
        Assertions.assertEquals(studentDto.email(),student.getEmail());
        Assertions.assertEquals(studentDto.age(),student.getAge());
        Assertions.assertNotNull(student.getSchool());
        Assertions.assertEquals(studentDto.schoolId(),student.getSchool().getId());
    }
}