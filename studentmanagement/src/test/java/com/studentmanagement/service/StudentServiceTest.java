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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

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
        StudentDto dto = new StudentDto("Sandeep","Kumar","sndp@mail.com",20, LocalDate.of(1995, 12, 16),"9988776655", LocalDate.of(2025,03,20),1);
        Student student = new Student("Sandeep","Kumar","sndp@mail.com",20, LocalDate.of(1995, 12, 16),"9988776655", LocalDate.of(2025,03,20));
        Student saveStudent = new Student("Sandeep","Kumar","sndp@mail.com",20, LocalDate.of(1995, 12, 16),"9988776655", LocalDate.of(2025,03,20));
        saveStudent.setId(1);

        // Mock the calls
        Mockito.when(mapper.toStudent(dto)).thenReturn(student);
        Mockito.when(repository.save(student)).thenReturn(saveStudent);
        Mockito.when(mapper.toStudentResponseDto(saveStudent)).thenReturn(new StudentResponseDto("Sandeep","Kumar","sndp@mail.com",20,LocalDate.of(1995,12,16),"9988776655"));

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

    @Test
    public void checkGetAll() {
        // Given
        List<Student> students = new ArrayList<>();
        students.add(new Student("Sandeep", "Kumar", "sndp@mail.com", 20, LocalDate.of(1995, 12, 16),"9988776655", LocalDate.of(2025,03,20)));

        // Mock
        Mockito.when(repository.findAll()).thenReturn(students);
        Mockito.when(mapper.toStudentResponseDto(any(Student.class))).thenReturn(new StudentResponseDto("Sandeep", "Kumar", "sndp@mail.com", 20, LocalDate.of(1995, 12, 16),"9988776655"));

        // When
        List<StudentResponseDto> responseDtos = studentService.getAll();

        // Then
        Assertions.assertEquals(students.size(), responseDtos.size(), "The size of response DTO list should match the size of the student list.");

        // Additional assertions to check the actual content
        Assertions.assertEquals("Sandeep", responseDtos.get(0).firstName());
        Assertions.assertEquals("Kumar", responseDtos.get(0).lastName());
        Assertions.assertEquals("sndp@mail.com", responseDtos.get(0).email());
        Assertions.assertEquals(20, responseDtos.get(0).age());

        // Verify that the repository and mapper methods were called
        Mockito.verify(repository).findAll();
        Mockito.verify(mapper).toStudentResponseDto(any(Student.class));

        Mockito.verify(repository,times(1)).findAll();
    }

    @Test
    public void checkFindById(){
        //Given
        Integer studentId = 1;

        Student student = new Student("Sandeep","Kumar","sndp@mail.com",25, LocalDate.of(1995, 12, 16),"9988776655", LocalDate.of(2025,03,20));
        StudentResponseDto responseDto = new StudentResponseDto("Sandeep","Kumar","sndp@mail.com",25, LocalDate.of(1995, 12, 16),"9988776655");

        //Mock
        Mockito.when(repository.findById(studentId)).thenReturn(Optional.of(student));
        Mockito.when(mapper.toStudentResponseDto(student)).thenReturn(responseDto);

        //when
        StudentResponseDto responseDto1 = studentService.findById(studentId);

        Assertions.assertEquals(responseDto1.firstName(),student.getFirstName());
        Assertions.assertEquals(responseDto1.lastName(),student.getLastName());
        Assertions.assertEquals(responseDto1.email(),student.getEmail());
        Assertions.assertEquals(responseDto1.age(),student.getAge());

        Mockito.verify(repository,times(1)).findById(studentId);

    }

}
