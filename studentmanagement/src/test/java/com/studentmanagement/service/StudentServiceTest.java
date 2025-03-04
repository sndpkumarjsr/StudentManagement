package com.studentmanagement.service;

import com.studentmanagement.dto.StudentDto;
import com.studentmanagement.dto.StudentResponseDto;
import com.studentmanagement.entity.Student;
import com.studentmanagement.repository.StudentRepository;
import com.studentmanagement.util.StudentMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentMapper mapper;

    @Mock
    private StudentRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void successfullySaveStudent() {
        StudentDto dto = new StudentDto("Sandeep","Kumar","sndp@mail.com",20,1);
        Student student = new Student("Sandeep","Kumar","sndp@mail.com",20);
        Student saveStudent = new Student("Sandeep","Kumar","sndp@mail.com",20);
        saveStudent.setId(1);

        // Mock the calls
        Mockito.when(mapper.toStudent(dto)).thenReturn(student);
        Mockito.when(repository.save(student)).thenReturn(saveStudent);
        Mockito.when(mapper.toStudentResponseDto(saveStudent)).thenReturn(new StudentResponseDto("Sandeep","Kumar","sndp@mail.com",20));

        // Call the service method
        StudentResponseDto responseDto = studentService.addStudent(dto);

        // Assert that response is not null
        Assertions.assertNotNull(responseDto, "Response DTO should not be null!");

        // Assert that the fields are correct
        Assertions.assertEquals(responseDto.firstName(), dto.firstName());
        Assertions.assertEquals(responseDto.lastName(), dto.lastName());
        Assertions.assertEquals(responseDto.email(), dto.email());
        Assertions.assertEquals(responseDto.age(), dto.age());

        Mockito.verify(mapper,Mockito.times(1)).toStudent(dto);
        Mockito.verify(repository,Mockito.times(1)).save(student);
        Mockito.verify(mapper,Mockito.times(1)).toStudentResponseDto(saveStudent);
    }


}
