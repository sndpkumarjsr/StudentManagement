package com.studentmanagement.service;

import com.studentmanagement.dto.GuardianDto;
import com.studentmanagement.dto.GuardianResponseDto;
import com.studentmanagement.entity.Guardian;
import com.studentmanagement.entity.Status;
import com.studentmanagement.repository.GuardianRepository;
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

    public GuardianService(GuardianRepository guardianRepository, GuardianMapper guardianMapper) {
        this.guardianRepository = guardianRepository;
        this.guardianMapper = guardianMapper;
    }

    public GuardianResponseDto add(GuardianDto guardianDto){
        Guardian guardian = guardianMapper.toGuardian(guardianDto);
        guardian.setCreatedBy("Admin");
        guardian.setCreatedAt(LocalDateTime.now());
        guardian.setStatus(Status.ACTIVE);

        Guardian saveGuardian =  guardianRepository.save(guardian);
        return guardianMapper.toGuardianResponseDto(saveGuardian);
    }

    public List<GuardianResponseDto> getAll(){
        return guardianRepository.findAll()
                .stream()
                .map(guardianMapper::toGuardianResponseDto)
                .collect(Collectors.toList());
    }

    public GuardianResponseDto getByEmail(String email){
        Optional<Guardian> guardianOpt = guardianRepository.findGuardianByEmail(email);

        if(guardianOpt.isPresent()){
            Guardian guardian = guardianOpt.get();
            return guardianMapper.toGuardianResponseDto(guardian);
        }
        return null;
    }
}
