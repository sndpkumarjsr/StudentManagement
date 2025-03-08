package com.studentmanagement.service;

import com.studentmanagement.dto.ExamTypeDto;
import com.studentmanagement.entity.ExamType;
import com.studentmanagement.repository.ExamTypeRepository;
import com.studentmanagement.util.ExamTypeMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExamTypeService {

    private final ExamTypeRepository examTypeRepository;
    private final ExamTypeMapper examTypeMapper;

    public ExamTypeService(ExamTypeRepository examTypeRepository, ExamTypeMapper examTypeMapper) {
        this.examTypeRepository = examTypeRepository;
        this.examTypeMapper = examTypeMapper;
    }

    public List<ExamTypeDto> getAll(){
        return examTypeRepository.findAll()
                .stream()
                .map(examTypeMapper::toExamTypeDto)
                .collect(Collectors.toList());
    }

    public ExamTypeDto add(ExamTypeDto examTypeDto){
        ExamType examType = examTypeMapper.toExamType(examTypeDto);
        if(examType == null)
            return null;
        ExamType savedExamType = examTypeRepository.save(examType);
        return examTypeMapper.toExamTypeDto(savedExamType);
    }

    public ExamTypeDto getByName(String examName){
        Optional<ExamType> examTypeOpt = examTypeRepository.findExamTypeByExamName(examName);
        if(examTypeOpt.isPresent()){
            ExamType examType = examTypeOpt.get();
            return examTypeMapper.toExamTypeDto(examType);
        }
        return null;
    }

}
