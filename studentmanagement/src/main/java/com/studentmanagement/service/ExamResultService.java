package com.studentmanagement.service;

import com.studentmanagement.dto.ExamResultDto;
import com.studentmanagement.dto.ExamResultResponseDto;
import com.studentmanagement.entity.Exam;
import com.studentmanagement.entity.ExamResult;
import com.studentmanagement.entity.Student;
import com.studentmanagement.repository.ExamRepository;
import com.studentmanagement.repository.ExamResultRepository;
import com.studentmanagement.repository.StudentRepository;
import com.studentmanagement.util.ExamResultMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExamResultService {

    private final ExamResultMapper examResultMapper;
    private final ExamResultRepository examResultRepository;
    private final StudentRepository studentRepository;
    private final ExamRepository examRepository;

    public ExamResultService(ExamResultMapper examResultMapper, ExamResultRepository examResultRepository, StudentRepository studentRepository, ExamRepository examRepository) {
        this.examResultMapper = examResultMapper;
        this.examResultRepository = examResultRepository;
        this.studentRepository = studentRepository;
        this.examRepository = examRepository;
    }

    public List<ExamResultResponseDto> getAll(){
        return examResultRepository.findAll()
                .stream().map(examResultMapper::toExamResultResponseDto)
                .collect(Collectors.toList());
    }

    public ExamResultResponseDto add(ExamResultDto examResultDto){
        ExamResult examResult =  new ExamResult();
        examResult.setMarks(examResultDto.marks());

        Optional<Student> studentOpt = studentRepository.findById(examResultDto.studentId());
        Optional<Exam> examOpt = examRepository.findById(examResultDto.examId());

        if(studentOpt.isPresent() && examOpt.isPresent()){
            Student student = studentOpt.get();
            Exam exam = examOpt.get();

            examResult.setExam(exam);
            examResult.setStudent(student);
            examResult.setCreatedAt(LocalDateTime.now());
            examResult.setCreatedBy("Admin");
            ExamResult saveExamResult = examResultRepository.save(examResult);
            return examResultMapper.toExamResultResponseDto(examResult);
        }
        return null;
    }
}
