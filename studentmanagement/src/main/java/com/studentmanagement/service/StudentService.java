package com.studentmanagement.service;

import com.studentmanagement.dto.StudentDto;
import com.studentmanagement.dto.StudentResponseDto;
import com.studentmanagement.entity.Student;
import com.studentmanagement.repository.StudentRepository;
import com.studentmanagement.util.StudentMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public List<StudentResponseDto> getAll(){
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toStudentResponseDto)
                .collect(Collectors.toList());
    }

    public StudentResponseDto addStudent(StudentDto studentDto){
        Student student = studentMapper.toStudent(studentDto);
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toStudentResponseDto(savedStudent);
    }
}
