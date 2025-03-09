package com.studentmanagement.service;

import com.studentmanagement.dto.ExamDto;
import com.studentmanagement.dto.ExamResponseDto;
import com.studentmanagement.entity.Exam;
import com.studentmanagement.entity.ExamType;
import com.studentmanagement.repository.ExamRepository;
import com.studentmanagement.repository.ExamTypeRepository;
import com.studentmanagement.util.ExamMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExamService {

    private final ExamMapper examMapper;
    private final ExamRepository examRepository;
    private final ExamTypeRepository examTypeRepository;

    public ExamService(ExamMapper examMapper, ExamRepository examRepository, ExamTypeRepository examTypeRepository) {
        this.examMapper = examMapper;
        this.examRepository = examRepository;
        this.examTypeRepository = examTypeRepository;
    }

    public List<ExamResponseDto> getAll(){
        return examRepository.findAll()
                .stream()
                .map(examMapper::toExamResponseDto)
                .collect(Collectors.toList());
    }

    public ExamResponseDto add(ExamDto examDto){
        Exam exam = examMapper.toExam(examDto);
        exam.setCreatedBy("Admin");
        exam.setCreatedAt(LocalDateTime.now());
        Optional<ExamType> examTypeOpt = examTypeRepository.findById(examDto.examTypeId());
        if(examTypeOpt.isPresent()){
            ExamType examType = examTypeOpt.get();
            exam.setExamType(examType);
            Exam saveExam = examRepository.save(exam);
            return examMapper.toExamResponseDto(saveExam);
        }
        return null;
    }

}
