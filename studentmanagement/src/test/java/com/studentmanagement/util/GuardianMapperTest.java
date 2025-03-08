package com.studentmanagement.util;

import com.studentmanagement.dto.GuardianDto;
import com.studentmanagement.dto.GuardianResponseDto;
import com.studentmanagement.entity.Guardian;
import com.studentmanagement.entity.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GuardianMapperTest {

    private  GuardianMapper guardianMapper;

    @BeforeEach
    void setUp() {
        guardianMapper = new GuardianMapper();
    }

    @Test
    public void checkConvertToGuardian(){
        GuardianDto guardianDto = new GuardianDto("ABC","DEF", LocalDate.of(1990,10,25),"9988556622","abc@mail.com","abc@123");

        Guardian guardian = guardianMapper.toGuardian(guardianDto);

        Assertions.assertEquals(guardianDto.firstName(),guardian.getFirstName());
        Assertions.assertEquals(guardianDto.lastName(),guardian.getLastName());
        Assertions.assertEquals(guardianDto.email(),guardian.getEmail());
        Assertions.assertEquals(guardianDto.password(),guardian.getPassword());
        Assertions.assertEquals(guardianDto.dateOfBirth(),guardian.getDateOfBirth());
        Assertions.assertEquals(guardianDto.phone(),guardian.getPhone());

    }

    @Test
    public void checkGuardianResponseDto(){
        Guardian guardian = new Guardian("abc@mail.com","abc@123","ABC","DEF", LocalDate.of(1990,10,25),"9988556622");
        Student student = new Student("Sandeep","Kumar","sndp@mail.com","sndp@123",15, LocalDate.of(1995, 12, 16),"9988776655", LocalDate.of(2025,03,20));
        guardian.setStudents(List.of(student));

        GuardianResponseDto guardianResponseDto = guardianMapper.toGuardianResponseDto(guardian);

        Assertions.assertEquals(guardian.getEmail(),guardianResponseDto.email());
        Assertions.assertEquals(guardian.getPhone(),guardianResponseDto.phone());
        Assertions.assertEquals(guardian.getFirstName(),guardianResponseDto.firstName());
        Assertions.assertEquals(guardian.getLastName(),guardianResponseDto.lastName());
        Assertions.assertEquals(guardian.getDateOfBirth(),guardianResponseDto.dateOfBirth());
        Assertions.assertEquals(guardian.getStudents(),guardianResponseDto.students());
    }
}