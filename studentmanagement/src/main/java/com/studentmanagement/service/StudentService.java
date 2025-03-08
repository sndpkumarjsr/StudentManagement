package com.studentmanagement.service;

import com.studentmanagement.dto.StudentDto;
import com.studentmanagement.dto.StudentResponseDto;
import com.studentmanagement.entity.Guardian;
import com.studentmanagement.entity.School;
import com.studentmanagement.entity.Status;
import com.studentmanagement.entity.Student;
import com.studentmanagement.repository.GuardianRepository;
import com.studentmanagement.repository.SchoolRepository;
import com.studentmanagement.repository.StudentRepository;
import com.studentmanagement.util.StudentMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final SchoolRepository schoolRepository;
    private final GuardianRepository guardianRepository;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper, SchoolRepository schoolRepository, GuardianRepository guardianRepository) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.schoolRepository = schoolRepository;
        this.guardianRepository = guardianRepository;
    }

    public List<StudentResponseDto> getAll(){
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toStudentResponseDto)
                .collect(Collectors.toList());
    }

    public StudentResponseDto addStudent(StudentDto studentDto){
        Student student = studentMapper.toStudent(studentDto);
        student.setCreatedBy("Admin");
        student.setCreatedAt(LocalDateTime.now());
        student.setStatus(Status.ACTIVE );
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toStudentResponseDto(savedStudent);
    }

    public StudentResponseDto findById(Integer studentId){
        return studentRepository.findById(studentId)
                .map(studentMapper::toStudentResponseDto)
                .orElse(null);
    }

    public StudentResponseDto mapSchool(Integer studentId, Integer schoolId){
        Optional<School> schoolOpt = schoolRepository.findById(schoolId);
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        if(schoolOpt.isPresent() && studentOpt.isPresent()){
            School school = schoolOpt.get();
            Student student = studentOpt.get();
            student.setSchool(school);
            Student saveStudent = studentRepository.save(student);
            return  studentMapper.toStudentResponseDto(saveStudent);
        }
        return null;
    }

    public StudentResponseDto mapGuardian(Integer studentId, Integer guardianId){
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        Optional<Guardian> guardianOpt = guardianRepository.findById(guardianId);

        if(studentOpt.isPresent() && guardianOpt.isPresent()){
            Guardian guardian = guardianOpt.get();
            Student student = studentOpt.get();
            student.setGuardian(guardian);
            Student saveStudent = studentRepository.save(student);
            return studentMapper.toStudentResponseDto(saveStudent);
        }
        return null;
    }

}
