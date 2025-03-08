package com.studentmanagement.util;

import com.studentmanagement.dto.GuardianDto;
import com.studentmanagement.dto.GuardianResponseDto;
import com.studentmanagement.entity.Guardian;
import org.springframework.stereotype.Service;

@Service
public class GuardianMapper {

    public Guardian toGuardian(GuardianDto guardianDto){
        return new Guardian(guardianDto.email(),guardianDto.password(),guardianDto.firstName(),guardianDto.lastName(),guardianDto.dateOfBirth(),guardianDto.phone());
    }

    public GuardianResponseDto toGuardianResponseDto(Guardian guardian){
        return new GuardianResponseDto(guardian.getFirstName(),guardian.getLastName(),guardian.getDateOfBirth(),guardian.getPhone(),guardian.getEmail(),guardian.getStudents());
    }
}
