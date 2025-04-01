package com.studentmanagement.service;

import com.studentmanagement.dto.ExamTypeDto;
import com.studentmanagement.entity.ExamType;
import com.studentmanagement.repository.ExamTypeRepository;
import com.studentmanagement.util.ExamTypeMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        examType.setCreatedBy("Admin");
        examType.setCreatedAt(LocalDateTime.now());
        ExamType savedExamType = examTypeRepository.save(examType);
        return examTypeMapper.toExamTypeDto(savedExamType);
    }

    public ExamTypeDto getByName(String examName){
        Optional<ExamType> examTypeOpt = examTypeRepository.findExamTypeByExamName(examName);
        if(examTypeOpt.isPresent()){
            ExamType examType = examTypeOpt.get();
            return examTypeMapper.toExamTypeDto(examType);
        }
        throw new IllegalArgumentException("Exam Type not found!!!");
    }

    public ExamTypeDto update(ExamTypeDto dto, String examName){
        Optional<ExamType> examTypeOpt = examTypeRepository.findExamTypeByExamName(examName);
        if(dto.examName().equals(examName) && examTypeOpt.isPresent()){
            ExamType examType = examTypeOpt.get();
            examType.setTotalMarks(dto.totalMarks());
            examType.setPassMarks(dto.passMarks());
            examType.setDescription(dto.description());
            examType.setModifiedBy("Admin");
            examType.setModifiedAt(LocalDateTime.now());

            ExamType update = examTypeRepository.save(examType);
            return examTypeMapper.toExamTypeDto(update);
        }
        throw new IllegalArgumentException("Exam Type not found!!!");
    }

    public boolean delete(String examName){
        Optional<ExamType> examTypeOpt = examTypeRepository.findExamTypeByExamName(examName);
        if(examTypeOpt.isPresent()){
            ExamType examType = examTypeOpt.get();
            examTypeRepository.delete(examType);
            return true;
        }
        throw new IllegalArgumentException("Exam Type not found!!!");
    }

}
