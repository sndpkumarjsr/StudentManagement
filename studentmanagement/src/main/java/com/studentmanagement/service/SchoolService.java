package com.studentmanagement.service;

import com.studentmanagement.dto.SchoolDto;
import com.studentmanagement.entity.School;
import com.studentmanagement.repository.SchoolRepository;
import com.studentmanagement.util.SchoolMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;

    public SchoolService(SchoolRepository schoolRepository, SchoolMapper schoolMapper) {
        this.schoolRepository = schoolRepository;
        this.schoolMapper = schoolMapper;
    }

    public List<SchoolDto> getAll(){
        return schoolRepository.findAll()
                .stream()
                .map(schoolMapper::toSchoolDto)
                .collect(Collectors.toList());
    }

    public SchoolDto addSchool(SchoolDto schoolDto){
        School school = new School(schoolDto.name());
        School savedSchool = schoolRepository.save(school);
        return schoolMapper.toSchoolDto(savedSchool);
    }

}
