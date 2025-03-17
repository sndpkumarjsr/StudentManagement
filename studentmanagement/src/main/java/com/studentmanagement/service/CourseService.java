package com.studentmanagement.service;

import com.studentmanagement.dto.CourseDto;
import com.studentmanagement.dto.CourseResponseDto;
import com.studentmanagement.entity.Course;
import com.studentmanagement.entity.Grade;
import com.studentmanagement.repository.CourseRepository;
import com.studentmanagement.repository.GradeRepository;
import com.studentmanagement.util.CourseMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final GradeRepository gradeRepository;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper, GradeRepository gradeRepository) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
        this.gradeRepository = gradeRepository;
    }

    public List<CourseResponseDto> getAll(){
        return courseRepository.findAll()
                .stream().map(courseMapper::toCourseResponseDto)
                .collect(Collectors.toList());
    }

    public CourseDto add(CourseDto dto){
        Course course = courseMapper.toCourse(dto);
        course.setCreatedBy("Admin");
        course.setCreatedAt(LocalDateTime.now());

        Course savedCourse = courseRepository.save(course);

        if(savedCourse != null)
            return courseMapper.toCourseDto(savedCourse);
        return null;
    }

    public CourseResponseDto mapToGrade(Integer courseId, Integer gradeId){
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        Optional<Grade> gradeOpt = gradeRepository.findById(gradeId);

        if(courseOpt.isPresent() && gradeOpt.isPresent()){
            Course course = courseOpt.get();
            Grade grade = gradeOpt.get();

            course.setGrade(grade);

            Course savedCourse = courseRepository.save(course);

            return courseMapper.toCourseResponseDto(savedCourse);
        }
        return null;
    }
}
