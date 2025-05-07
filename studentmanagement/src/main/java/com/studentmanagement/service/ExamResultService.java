package com.studentmanagement.service;


import com.studentmanagement.dto.ExamResponseDto;
import com.studentmanagement.dto.ExamResultDto;
import com.studentmanagement.dto.ExamResultResponseDto;
import com.studentmanagement.entity.Course;
import com.studentmanagement.entity.Exam;
import com.studentmanagement.entity.ExamResult;
import com.studentmanagement.entity.Student;
import com.studentmanagement.repository.CourseRepository;
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
    private final CourseRepository courseRepository;

    public ExamResultService(ExamResultMapper examResultMapper, ExamResultRepository examResultRepository, StudentRepository studentRepository, ExamRepository examRepository, CourseRepository courseRepository) {
        this.examResultMapper = examResultMapper;
        this.examResultRepository = examResultRepository;
        this.studentRepository = studentRepository;
        this.examRepository = examRepository;
        this.courseRepository = courseRepository;
    }

    public List<ExamResultResponseDto> getAll(){
        return examResultRepository.findAll()
                .stream().map(examResultMapper::toExamResultResponseDto)
                .collect(Collectors.toList());
    }

    public ExamResultResponseDto add(ExamResultDto examResultDto) {
        ExamResult examResult = new ExamResult();
        examResult.setMarks(examResultDto.marks());

        // Fetch student and exam
        Optional<Student> studentOpt = studentRepository.findByAdmissionNumber(examResultDto.admissionNumber());
        Optional<Exam> examOpt = examRepository.findByNameAndExamTypeExamName(examResultDto.examName(), examResultDto.examTypeName());
        Optional<Course> courseOpt = courseRepository.findByNameAndDescription(examResultDto.courseName(), examResultDto.description());


        if (studentOpt.isPresent() && examOpt.isPresent() && courseOpt.isPresent()) {
            Student student = studentOpt.get();
            Exam exam = examOpt.get();
            Course course = courseOpt.get();

            // Set exam and student to the result
            examResult.setExam(exam);
            examResult.setStudent(student);
            examResult.setCourse(course);
            examResult.setCreatedAt(LocalDateTime.now());
            examResult.setCreatedBy("Admin");

            // Save and return response
            ExamResult saveExamResult = examResultRepository.save(examResult);
            return examResultMapper.toExamResultResponseDto(saveExamResult);
        }

        // Add logging or return a specific response
        throw new IllegalArgumentException("Student or Exam not found.");
    }

    public ExamResultResponseDto updateMarks(ExamResultDto dto){
        Optional<ExamResult> examResultOpt = examResultRepository.findByExam_NameAndStudent_AdmissionNumberAndCourse_Name(dto.examName(),dto.admissionNumber(), dto.courseName());
        if(examResultOpt.isPresent()){
            ExamResult examResult = examResultOpt.get();
            examResult.setMarks(dto.marks());

            ExamResult update = examResultRepository.save(examResult);
            return examResultMapper.toExamResultResponseDto(update);
        }
        throw new IllegalArgumentException("Data not found!!!");
    }

}
