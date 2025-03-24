package com.studentmanagement.service;

import com.studentmanagement.dto.GuardianDto;
import com.studentmanagement.dto.GuardianResponseDto;
import com.studentmanagement.dto.StudentResponseDto;
import com.studentmanagement.entity.Guardian;
import com.studentmanagement.entity.Status;
import com.studentmanagement.entity.Student;
import com.studentmanagement.repository.GuardianRepository;
import com.studentmanagement.repository.StudentRepository;
import com.studentmanagement.util.GuardianMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GuardianService {

    private final GuardianRepository guardianRepository;
    private final GuardianMapper guardianMapper;
    private final StudentRepository studentRepository;
    private final StudentService studentService;

    public GuardianService(GuardianRepository guardianRepository, GuardianMapper guardianMapper, StudentRepository studentRepository, StudentService studentService) {
        this.guardianRepository = guardianRepository;
        this.guardianMapper = guardianMapper;
        this.studentRepository = studentRepository;
        this.studentService = studentService;
    }

    public GuardianResponseDto add(GuardianDto guardianDto){
        Optional<Guardian> guardianOptional =  guardianRepository.findGuardianByEmail(guardianDto.email());
        Optional<Student> studentOpt = studentRepository.findByAdmissionNumber(guardianDto.admissionNumber());
        if(guardianOptional.isPresent() && studentOpt.isPresent()){
            Guardian guardian = guardianOptional.get();
            guardian.setFirstName(guardianDto.firstName());
            guardian.setLastName(guardianDto.lastName());
            guardian.setEmail(guardianDto.email());
            guardian.setPhone(guardianDto.phone());
            guardian.setDateOfBirth(guardianDto.dateOfBirth());
            guardian.setPassword(guardianDto.password());
            guardian.setModifiedBy("Admin");
            guardian.setModifiedAt(LocalDateTime.now());
            guardian.setStatus(Status.ACTIVE);
            Guardian saveGuardian = guardianRepository.save(guardian);

            Student student = studentOpt.get();
            StudentResponseDto savedStudent = studentService.mapGuardian(guardianDto.admissionNumber(),saveGuardian.getEmail());
            return savedStudent.guardian();
        }else{
            Guardian guardian = guardianMapper.toGuardian(guardianDto);
            if(studentOpt.isPresent()) {
                Student student = studentOpt.get();
                guardian.setCreatedBy("Admin");
                guardian.setCreatedAt(LocalDateTime.now());
                guardian.setStatus(Status.ACTIVE);
                Guardian saveGuardian = guardianRepository.save(guardian);
                StudentResponseDto savedStudent = studentService.mapGuardian(guardianDto.admissionNumber(),saveGuardian.getEmail());
                return savedStudent.guardian();
            }
            throw new IllegalArgumentException("Admission Number is Invalid");
        }
    }

    public List<GuardianResponseDto> getAll(){
        return guardianRepository.findAll()
                .stream().filter(i->i.getStatus().toString().equals("ACTIVE"))
                .map(guardianMapper::toGuardianResponseDto)
                .collect(Collectors.toList());
    }

    public GuardianResponseDto getByEmail(String email){
        Optional<Guardian> guardianOpt = guardianRepository.findGuardianByEmail(email);
        if(guardianOpt.isPresent()){
            Guardian guardian = guardianOpt.get();
            return guardianMapper.toGuardianResponseDto(guardian);
        }
        throw new IllegalArgumentException("User not found");
    }

    public GuardianResponseDto update(GuardianDto dto,String email){
        Optional<Guardian> guardianOpt =  guardianRepository.findGuardianByEmail(email);
        if(guardianOpt.isPresent()){
            Guardian guardian = guardianOpt.get();
            guardian.setFirstName(dto.firstName());
            guardian.setLastName(dto.lastName());
            guardian.setEmail(dto.email());
            guardian.setDateOfBirth(dto.dateOfBirth());
            guardian.setPhone(dto.phone());
            guardian.setModifiedBy(dto.email());
            guardian.setModifiedAt(LocalDateTime.now());

            Guardian update = guardianRepository.save(guardian);
            return guardianMapper.toGuardianResponseDto(update);
        }
        throw new IllegalArgumentException("Invalid Authenticaltion");
    }

    public boolean delete(String email){
        Optional<Guardian> guardianOpt = guardianRepository.findGuardianByEmail(email);
        if(guardianOpt.isPresent() && guardianOpt.get().getStudents().stream().filter(i->i.getStatus().toString().equals("INACTIVE")).count() == guardianOpt.get().getStudents().size()){
            Guardian guardian = guardianOpt.get();
            guardian.setStatus(Status.INACTIVE);
            guardian.setModifiedBy(email);
            guardian.setModifiedAt(LocalDateTime.now());

            Guardian savedGuardian = guardianRepository.save(guardian);
            return true;
        }

        throw new IllegalArgumentException("User not found or student is attached");
    }
}
