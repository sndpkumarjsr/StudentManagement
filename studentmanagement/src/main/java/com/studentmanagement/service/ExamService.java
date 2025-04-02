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
        Optional<ExamType> examTypeOpt = examTypeRepository.findExamTypeByExamName(examDto.examType_examName());
        if(examTypeOpt.isPresent()){
            ExamType examType = examTypeOpt.get();
            exam.setExamType(examType);
            Exam saveExam = examRepository.save(exam);
            return examMapper.toExamResponseDto(saveExam);
        }
        throw new IllegalArgumentException("Exam name not found");
    }

    public ExamResponseDto getByNameAndExamtypeName(String name,String examType_examName){
        Optional<Exam> examOpt = examRepository.findByNameAndExamTypeExamName(name,examType_examName);
        if(examOpt.isPresent()){
            Exam exam = examOpt.get();
            return examMapper.toExamResponseDto(exam);
        }
        throw new IllegalArgumentException("Not found!!!");
    }

    public ExamResponseDto update(ExamDto dto, String name,String examType_examName){
        Optional<Exam> examOpt = examRepository.findByNameAndExamTypeExamName(name,examType_examName);
        if(examOpt.isPresent()){
            Exam exam = examOpt.get();
            exam.setStartDate(dto.startDate());
            exam.setName(dto.name());
            exam.setModifiedBy("Admin");
            exam.setModifiedAt(LocalDateTime.now());

            Exam update = examRepository.save(exam);
            return examMapper.toExamResponseDto(update);
        }
        throw new IllegalArgumentException("Not found!!!");
    }

    public boolean delete(String name,String examType_examName){
        Optional<Exam> examOpt = examRepository.findByNameAndExamTypeExamName(name,examType_examName);
        if(examOpt.isPresent()){
            Exam exam = examOpt.get();
            examRepository.delete(exam);
            return true;
        }
        throw new IllegalArgumentException("Not found!!!");
    }
}
