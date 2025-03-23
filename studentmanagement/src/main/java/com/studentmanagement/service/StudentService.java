package com.studentmanagement.service;

import com.studentmanagement.dto.StudentDto;
import com.studentmanagement.dto.StudentResponseDto;
import com.studentmanagement.entity.*;
import com.studentmanagement.repository.ClassRoomRepository;
import com.studentmanagement.repository.GuardianRepository;
import com.studentmanagement.repository.SchoolRepository;
import com.studentmanagement.repository.StudentRepository;
import com.studentmanagement.util.StudentMapper;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
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
    private final ClassRoomRepository classRoomRepository;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper, SchoolRepository schoolRepository, GuardianRepository guardianRepository, ClassRoomRepository classRoomRepository) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.schoolRepository = schoolRepository;
        this.guardianRepository = guardianRepository;
        this.classRoomRepository = classRoomRepository;
    }

    public List<StudentResponseDto> getAll(){
        return studentRepository.findAll()
                .stream().filter(i->i.getStatus().toString().equals("ACTIVE"))
                .map(studentMapper::toStudentResponseDto)
                .collect(Collectors.toList());
    }

    public StudentResponseDto addStudent(StudentDto studentDto){
        Student student = studentMapper.toStudent(studentDto);
        student.setCreatedBy("Admin");
        student.setCreatedAt(LocalDateTime.now());
        student.setStatus(Status.ACTIVE );
        Student savedStudent = studentRepository.save(student);
        if(savedStudent != null)
            return studentMapper.toStudentResponseDto(savedStudent);
        throw new IllegalArgumentException("Fail to save. Please check the fields.");
    }

    public StudentResponseDto findById(String admissionNumber){
        Optional<Student> studentOpt = studentRepository.findByAdmissionNumber(admissionNumber);
        if(studentOpt.isPresent() && studentOpt.get().getStatus().toString().equals("ACTIVE"))
            return studentMapper.toStudentResponseDto(studentOpt.get());
        throw new IllegalArgumentException("Student Not Found");
    }

    public StudentResponseDto mapSchool(Integer studentId, Integer schoolId){
        Optional<School> schoolOpt = schoolRepository.findById(schoolId);
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        if(schoolOpt.isPresent() && studentOpt.isPresent() && studentOpt.get().getStatus().toString().equals("ACTIVE")){
            School school = schoolOpt.get();
            Student student = studentOpt.get();
            student.setSchool(school);
            Student saveStudent = studentRepository.save(student);
            return  studentMapper.toStudentResponseDto(saveStudent);
        }
        throw new IllegalArgumentException("Fields is incorrect.");
    }

    public StudentResponseDto mapGuardian(Integer studentId, Integer guardianId){
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        Optional<Guardian> guardianOpt = guardianRepository.findById(guardianId);

        if(studentOpt.isPresent() && guardianOpt.isPresent() && studentOpt.get().getStatus().toString().equals("ACTIVE")){
            Guardian guardian = guardianOpt.get();
            Student student = studentOpt.get();
            student.setGuardian(guardian);
            Student saveStudent = studentRepository.save(student);
            return studentMapper.toStudentResponseDto(saveStudent);
        }
        throw new IllegalArgumentException("Fields is incorrect.");
    }

    public StudentResponseDto mapClassRoom(Integer studentId,Integer classroomId){
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        Optional<ClassRoom> classRoomOpt = classRoomRepository.findById(classroomId);

        if(studentOpt.isPresent() && classRoomOpt.isPresent() && studentOpt.get().getStatus().toString().equals("ACTIVE")){
            Student student = studentOpt.get();
            ClassRoom classRoom = classRoomOpt.get();

            student.getClassRooms().add(classRoom);
            classRoom.getStudents().add(student);

            Student savedStudent = studentRepository.save(student);
            ClassRoom savedClassRoom =  classRoomRepository.save(classRoom);

            return studentMapper.toStudentResponseDto(savedStudent);
        }
        throw new IllegalArgumentException("Fields is incorrect.");
    }

    public StudentResponseDto update(String admissionNumber,StudentDto dto){
        Optional<Student> studentOpt = studentRepository.findByAdmissionNumber(admissionNumber);
        if(studentOpt.isPresent() && studentOpt.get().getStatus().toString().equals("ACTIVE")){
            Student student = studentOpt.get();
            student.setFirstName(dto.firstName());
            student.setLastName(dto.lastName());
            student.setEmail(dto.email());
            student.setAge(dto.age());
            student.setDateOfBirth(dto.dateOfBirth());
            student.setDateOfjoining(dto.dateOfjoining());
            student.setModifiedBy(admissionNumber);
            student.setModifiedAt(LocalDateTime.now());

            Student updatedStudent = studentRepository.save(student);
            return studentMapper.toStudentResponseDto(updatedStudent);
        }
        throw new IllegalArgumentException("Student not found");
    }

    public boolean delete(String admissionNumber){
        Optional<Student> studentOpt = studentRepository.findByAdmissionNumber(admissionNumber);
        if(studentOpt.isPresent() && studentOpt.get().getStatus().toString().equals("ACTIVE")){
            Student student= studentOpt.get();
            student.setStatus(Status.INACTIVE);

            Student update = studentRepository.save(student);
            if(update != null)
                return true;
            return false;
        }
        throw new IllegalArgumentException("Student not found");
    }
}
